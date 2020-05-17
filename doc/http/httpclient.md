#### Http Client 的用法

上次介绍了 `Java` 中使用 `HttpUrlConnection` 调用 `web` 接口。这次带来更方便的一种方式



本文只介绍 `Http Client` 库的一般用法。详细的使用可以查看官方文档，英文不好的同学可以查看翻译版。

官方文档：<https://hc.apache.org/httpcomponents-client-ga/tutorial/html/index.html> 

翻译版：<https://blog.csdn.net/zhongzh86/article/details/84070561> 

ok，下面直接进入正题



使用前需要先引入依赖如下：

```xml
<!-- https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient -->
<dependency>
  <groupId>org.apache.httpcomponents</groupId>
  <artifactId>httpclient</artifactId>
  <version>4.5.12</version>
</dependency>
```



准备用于测试的两个 `web` 接口：

```java
@RestController
@RequestMapping("/demo")
public class HttpDemoController {
    @RequestMapping(path = "sentGet", method = RequestMethod.GET)
    public String getMethod(HttpServletRequest request){
        System.out.println("method: " + request.getMethod());
        System.out.println("content type: " + request.getContentType());

        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("parameters:------------------- ");
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()){
            System.out.println(entry.getKey() + "->" + entry.getValue()[0]);
        }
        System.out.println("------------------------------ ");

        return "GET 返回的数据";
    }

    @RequestMapping(path = "sentPost", method = RequestMethod.POST)
    public String postMethod(HttpServletRequest request) throws Exception{

        System.out.println("method: " + request.getMethod());
        System.out.println("content type: " + request.getContentType());

        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println("parameters:------------------- ");
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()){
            System.out.println(entry.getKey() + "->" + entry.getValue()[0]);
        }
        System.out.println("------------------------------ ");

        // 得到body的内容
        String s = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
        System.out.println("body: " + s);

        return "POST 返回的数据";
    }
}
```



使用 `Http Client` 的步骤：

1. 创建 `HttpClient` 对象
2. 确定 `URL` 和参数
3. 创建方法类型对象
4. 设置`body` 内容（GET可以不用）
5. 获取结果
6. 关闭资源



 `POST` 的方式的具体代码如下：

```Java
public static final String POST_URL = "http://127.0.0.1:8080/demo/sentPost";
public static void doPost() throws Exception{
    // 1.创建HttpClient对象
    CloseableHttpClient httpClient = HttpClients.createDefault();

    // 2. 确定URL和参数，这里设置的参数会被拼接到url后面
    URI uri = new URIBuilder(POST_URL)
            .addParameter("HeaderKey", "headerValue")
            .build();

    // 3.创建方法类型对象,也可以使用GetMethod对象（老版本）
    HttpPost httpPost = new HttpPost(uri);

    // 4.设置body参数，GET方式省略这一步
    // text/plain表示纯文本
    HttpEntity httpEntity = new StringEntity("body content",
            ContentType.create("text/plain", "UTF-8"));

    // 表单，会被处理后拼接到url中
    /*List<NameValuePair> forms = new ArrayList<>();
    forms.add(new BasicNameValuePair("userName","mkii"));
    forms.add(new BasicNameValuePair("password","1234"));
    HttpEntity httpEntity = new UrlEncodedFormEntity(forms, "UTF-8");*/

    httpPost.setEntity(httpEntity);

    // 5.获取结果
    CloseableHttpResponse response = httpClient.execute(httpPost);
    
    // 得到response header
    Header[] allHeaders = response.getAllHeaders();
    // 协议状态码等信息
    StatusLine statusLine = response.getStatusLine();

    // 返回body
    String s = EntityUtils.toString(response.getEntity());
    System.out.println(s);

    // 6.关闭资源
    response.close();
    httpClient.close();
}
```