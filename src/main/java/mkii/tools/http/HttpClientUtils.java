package mkii.tools.http;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * http client
 * 可用于替代 HttpUrlConnection
 *
 * @author mkii
 * @// TODO: 2020/5/14
 */
public class HttpClientUtils {

    public static final String GET_URL = "http://127.0.0.1:8080/demo/sentGet";
    public static final String POST_URL = "http://127.0.0.1:8080/demo/sentPost";

    public static void main(String[] args) throws Exception {
        doGet();
        //doPost();

    }

    public static void doGet() throws Exception{
        // 1.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2. 确定URL和参数，这里设置的参数会被拼接到url后面
        URI uri = new URIBuilder(GET_URL)
                .addParameter("HeaderKey", "headerValue")
                .build();

        // 3.创建GET类型对象,也可以使用GetMethod对象（老版本）
        HttpGet httpGet = new HttpGet(uri);

        // 4.获取结果
        CloseableHttpResponse response = httpClient.execute(httpGet);

        // 得到response header
        Header[] allHeaders = response.getAllHeaders();
        // 协议状态码等信息
        StatusLine statusLine = response.getStatusLine();

        HttpEntity entity = response.getEntity();
        String s = IOUtils.toString(entity.getContent(), StandardCharsets.UTF_8);
        System.out.println(s);

        // 5.关闭资源
        response.close();
        httpClient.close();
    }

    public static void doPost() throws Exception{
        // 1.创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 2. 确定URL和参数，这里设置的参数会被拼接到url后面
        URI uri = new URIBuilder(POST_URL)
                .addParameter("HeaderKey", "headerValue")
                .build();

        // 3.创建方法类型对象,也可以使用GetMethod对象（老版本）
        HttpPost httpPost = new HttpPost(uri);

        // 4.设置body参数

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
        Header[] allHeaders = response.getAllHeaders();
        StatusLine statusLine = response.getStatusLine();

        String s = EntityUtils.toString(response.getEntity());
        System.out.println(s);

        // 6.关闭资源
        response.close();
        httpClient.close();
    }


}
