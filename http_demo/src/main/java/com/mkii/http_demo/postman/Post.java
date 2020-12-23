package com.mkii.http_demo.postman;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
}
