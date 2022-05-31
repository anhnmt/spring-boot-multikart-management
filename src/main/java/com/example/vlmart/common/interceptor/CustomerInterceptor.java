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
public class CustomerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        log.info("\n-------- CustomerInterceptor.preHandle --- ");
        log.info("Current url: {}", request.getRequestURL());

        var customer = request.getSession().getAttribute("customer");
        log.info("User : {}", customer);

        if (!DataUtils.notNullOrEmpty(customer)) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        return true;
    }

}