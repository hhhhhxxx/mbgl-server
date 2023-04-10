package com.hhhhhx.mbgl.utils;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

@Slf4j
public class HttpUtil {

    public static RestTemplate getTemplate = new RestTemplate();
    public static RestTemplate postTemplate = new RestTemplate();
    public static ResponseEntity<String> get(String url, Map params) {

        postTemplate.getMessageConverters().set(1,new StringHttpMessageConverter(StandardCharsets.UTF_8));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        return postTemplate.exchange(url, HttpMethod.GET, entity, String.class,params);
    }

    public static ResponseEntity<JSONObject> post(String url, JSONObject params) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<JSONObject> request = new HttpEntity<>(params, headers);

        return postTemplate.postForEntity(url, request, JSONObject.class);
    }

}
