package com.mkii.http_demo.postman;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

@RestController
@RequestMapping("method/post")
public class Post {
    // 使用MultipartHttpServletRequest获取form-data的数据
    @PostMapping("form-data1")
    public Map postTest1(MultipartHttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map result = new HashMap<>(parameterMap);

        List<String> nameList = new ArrayList<>();
        for (Map.Entry<String, MultipartFile> entry : request.getFileMap().entrySet()) {
            nameList.add(entry.getValue().getOriginalFilename());
        }

        result.put("files", nameList);
        return result;
    }

    // 使用@RequestParam获取form-data的数据
    @PostMapping("form-data2")
    public Map<String, String> postTest2(@RequestParam("key1") String key1, @RequestParam("key2") String key2,
                                         @RequestParam("file1") MultipartFile file1, @RequestParam("file2") MultipartFile file2) {
        Map<String, String> result = new HashMap<>();
        result.put("key1", key1);
        result.put("key2", key2);
        result.put("file1", file1.getOriginalFilename());
        result.put("file2", file2.getOriginalFilename());

        return result;
    }

    // 使用@RequestBody注解获取body的内容
    @PostMapping("/raw/text1")
    public String postTest3(@RequestBody String body) {
        return body;
    }

    // 使用@RequestBody注解获取body的内容
    @PostMapping("/raw/text2")
    public String postTest4(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        // 使用了common-io库
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }

    // 使用@RequestBody注解获取body的内容并自动解析
    @PostMapping("/raw/json1")
    public JsonBean postTest5(@RequestBody JsonBean jsonBean) {
        return jsonBean;
    }

    // 使用@RequestBody注解获取body的内容
    @PostMapping("/raw/json2")
    public String postTest6(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        // 使用了common-io库
        return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
    }

    @PostMapping("/raw/binary")
    public String postTest7(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        Path path = Paths.get("binary");
        // binary文件，如果文件存在则覆盖
        Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);
        return "written to " + path.toFile().getName();
    }
}

class JsonBean{
    private Long id;
    private String name;
    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
}
