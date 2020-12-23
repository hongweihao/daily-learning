package com.mkii.http_demo.postman;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

@RestController
@RequestMapping("header")
public class Header {

    // 使用HttpServletRequest对象获取全部header
    @RequestMapping("get1")
    public String headerTest1(HttpServletRequest request){
        StringBuilder builder = new StringBuilder("all header: \n");
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerKey = headerNames.nextElement();
            String headerValue = request.getHeader(headerKey);
            builder.append(headerKey);
            builder.append(": ");
            builder.append(headerValue);
            builder.append("\n");
        }
        return builder.toString();
    }

    // 使用@RequestHeader注解获取单个header
    @RequestMapping("get2")
    public String headerTest2(@RequestHeader("token") String token){
        return "token: " + token;
    }

    // 使用@RequestHeader注解获取全部header
    @RequestMapping("get3")
    public Map<String, String> headerTest3(@RequestHeader Map<String, String> headers){
        return headers;
    }
}