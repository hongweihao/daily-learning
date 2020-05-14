package mkii.tools.http;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Http方式调用海沧短信网关接口
 * 
 * @author karl
 * @// TODO: 2020/4/26
 */
public class HttpUrlConnectionUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUrlConnectionUtils.class);

    /**
     * post方式调用接口
     *
     * @param url 接口地址
     * @param params 参数列表
     * @return null if exception，xml string if success
     */
    public static String doPost(String url, String params) {
        OutputStream out = null;
        DataOutputStream dataOutputStream = null;
        InputStream in = null;
        ByteArrayOutputStream baos = null;
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            HttpURLConnection httpUrlConnection = (HttpURLConnection) urlConnection;
            // 设置是否向httpUrlConnection输出，post请求，参数要放在http正文内，因此需要设为true,
            // 默认情况下是false;
            httpUrlConnection.setDoOutput(true);
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpUrlConnection.setDoInput(true);
            // 忽略缓存
            httpUrlConnection.setUseCaches(false);
            // 设定请求的方法为"POST"，默认是GET
            httpUrlConnection.setRequestMethod("POST");
            // 设置http头
            httpUrlConnection.setRequestProperty("Content-type","application/json");
            httpUrlConnection.setRequestProperty("Charset", "UTF-8");
            httpUrlConnection.connect();

            out = httpUrlConnection.getOutputStream();
            dataOutputStream = new DataOutputStream(out);
            dataOutputStream.write(params.getBytes(StandardCharsets.UTF_8));
            dataOutputStream.flush();
            out.flush();

            // 获得响应状态
            int responseCode = httpUrlConnection.getResponseCode();
            if (HttpURLConnection.HTTP_OK != responseCode) {
                return null;
            }

            // 成功响应
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            in = httpUrlConnection.getInputStream();
            while ((len = in.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
                baos.flush();
            }
            return baos.toString("UTF-8");
        } catch (Exception e) {
            logger.error("HttpUrlConnectionUtils doPost: failed", e);
            return null;
        } finally {
            closeConnection(baos, in, dataOutputStream, out);
        }
    }

    public static void closeConnection(ByteArrayOutputStream baos, InputStream in, DataOutputStream dos, OutputStream out){
        if (baos != null) {
            try {
                baos.close();
            } catch (IOException e) {
                logger.error("HttpUrlConnectionUtils closeConnection: failed", e);
            }
        }
        if (in != null) {
            try {
                in.close();
            } catch (IOException e) {
                logger.error("HttpUrlConnectionUtils closeConnection: failed", e);
            }
        }
        if (dos != null) {
            try {
                dos.close();
            } catch (IOException e) {
                logger.error("HttpUrlConnectionUtils closeConnection: failed", e);
            }
        }
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                logger.error("HttpUrlConnectionUtils closeConnection: failed", e);
            }
        }
    }

    /**
     * 组建请求参数
     *
     * @param params parameters map
     */
    public static String getRequestBody(Map<String, String> params){
        Gson gson = new Gson();
        return gson.toJson(params);
    }
}
