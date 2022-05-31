package com.example.vlmart.common.interceptor;

import com.example.vlmart.common.DataUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        log.info("\n-------- AdminInterceptor.preHandle --- ");
        log.info("Current url: {}", request.getRequestURL());

        var user = request.getSession().getAttribute("user");
        log.info("User : {}", user);

        if (!DataUtils.notNullOrEmpty(user)) {
            response.sendRedirect(request.getContextPath() + "/dashboard/login");
            return false;
        }

        return true;
    }

}
