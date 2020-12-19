package com.mkii.http_demo.postman;


import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("method/get")
public class Get {
    // 定义同名变量接收参数
    @GetMapping("user1")
    public Map<String, Object> userTest1(Integer id, String user_name) {
        Map<String, Object> map = new HashMap<>();

        StringBuilder result = new StringBuilder();
        result.append("id: ");
        result.append(id);
        result.append(", user_name: ");
        result.append(user_name);

        System.out.printf("received: %s\n", result);

        map.put("result", result);
        return map;
    }

    // 使用@RequestParam指定变量
    @GetMapping("user2")
    public Map<String, Object> userTest2(@RequestParam("id") Integer id, @RequestParam("user_name") String userName) {
        Map<String, Object> map = new HashMap<>();

        StringBuilder result = new StringBuilder();
        result.append("id: ");
        result.append(id);
        result.append(", user_name: ");
        result.append(userName);

        System.out.printf("received: %s\n", result);

        map.put("result", result);
        return map;
    }

    // 通过HttpServletRequest获取url参数
    @GetMapping("user3")
    public Map<String, Object> userTest3(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        String result = request.getQueryString();
        System.out.printf("received: %s\n", result);

        map.put("result", result);
        return map;
    }

    // 通过@PathVariable获取url参数
    @GetMapping("user4/{id}/{user_name}")
    public Map<String, Object> userTest4(@PathVariable("id") Integer id, @PathVariable("user_name") String userName) {
        Map<String, Object> map = new HashMap<>();

        StringBuilder result = new StringBuilder();
        result.append("id: ");
        result.append(id);
        result.append(", user_name: ");
        result.append(userName);

        System.out.printf("received: %s\n", result);

        map.put("result", result);
        return map;
    }
}
