package com.jonas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shenjy
 * @createTime 2022/7/4 11:01
 * @description UserController
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String API = "http://service-provider/user/getUserName?userId={userId}";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("getName")
    public String getName(int userId) {
        Map<String, Object> args = new HashMap<String, Object>() {{
           put("userId", userId);
        }};
        return restTemplate.getForObject(API, String.class, args);
    }

    @PostMapping("postName")
    public String postName(Integer userId) {
        MultiValueMap<String, Integer> request = new LinkedMultiValueMap<>();
        request.add("userId", userId);
        URI uri = URI.create("http://service-provider/user/getUserName");
        return restTemplate.postForObject(uri, request, String.class);
    }
}
