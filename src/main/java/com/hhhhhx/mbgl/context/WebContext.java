package com.hhhhhx.mbgl.context;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 环境上下文
 *
 * @author zuck
 */
@Component
public class WebContext {

    public Integer getCurrentUserId() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        // 获取请求体 request
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();


        Integer userId = null;

        // Enumeration<String> headerNames = request.getHeaderNames();

        try {
            String str  = request.getHeader("user_id");
            userId = Integer.parseInt(str);
        } catch (Exception e) {
            throw new RuntimeException("user_id异常");
        }

        return userId;
    }
}
