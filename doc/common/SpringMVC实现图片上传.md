##### 1.在mvc配置文件中加入bean以及对应的依赖
```
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
    <!-- 设置默认编码 -->
    <property name="defaultEncoding" value="utf-8"></property>
    <!-- 上传图片最大大小5M-->
    <property name="maxUploadSize" value="5242440"></property>
</bean>
```
pom.xml
```
<dependency>
	<groupId>commons-fileupload</groupId>
	<artifactId>commons-fileupload</artifactId>
	<version>1.3.1</version>
</dependency>
```
##### 2.编写简单的测试页面
>  主要是设置`form`的`enctype="multipart/form-data"`
```
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图片上传</title>
</head>
<body>
<form name="uploadImg" method="post" action="/ssm/file/upload" enctype="multipart/form-data">
    <input type="file" name="file" multiple accept="image/*"/>
    <input type="submit"/>
</form>
</body>
</html>
```
> `mutiple`：多选
> `accept`：允许上传的格式，这里是只允许图片
##### 3.编写controller层代码，接收表单的内容
```
@Controller
@RequestMapping("/file")
public class UploadImgController {
    
    @RequestMapping("/upload")
    @ResponseBody
    public String uploadImg(@RequestParam("file") MultipartFile[] files, HttpServletRequest httpServletRequest){
        List<String> imgNameList = new ArrayList<String>();
        for(int i = 0; i < files.length; i++) {
            String fileName = files[i].getOriginalFilename();  //得到上传的文件名
            imgNameList.add(fileName);  //测试，，，不更改图片名
        }
        boolean f = FileUpUtil.upfiles(files, imgNameList);
        return f ? "success" : "error";
    }
}
```
> `@RequestParam("file") MultipartFile[] files`： 获取页面`name=‘file’`的域的内容
> `HttpServletRequest`：一般用来获取路径，获取小程序请求的formData，不需要可以去掉
> `imgNameList`：个人业务需要，不需要可以去掉
##### 4.图片上传工具类`FileUpUtil`
```
public class FileUpUtil {
    public static boolean upfiles(MultipartFile files[], List<String> imgName){
        // 上传位置
        String path = "G:/uploadImg/";  

        File f = new File(path);
        if (!f.exists())
            f.mkdirs();

        for (int i = 0; i < files.length; i++) {

            // 新文件名
            String newFileName = imgName.get(i);

            if (!files[i].isEmpty()) {
                try {
                    FileOutputStream fos = new FileOutputStream(path + newFileName);
                    InputStream in = files[i].getInputStream();
                    int b = 0;
                    while ((b = in.read()) != -1) {
                        fos.write(b);
                    }
                    fos.close();
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return true;
    }
}
```
> 同样，如果不需要`imgNameList`可以去掉

> 参考：[https://blog.csdn.net/qq_41950069/article/details/81354884](https://blog.csdn.net/qq_41950069/article/details/81354884)