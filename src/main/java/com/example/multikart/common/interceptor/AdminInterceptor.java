package com.example.multikart.common.interceptor;

import com.example.multikart.common.DataUtils;
import com.example.multikart.domain.model.User;
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

        var user = (User) request.getSession().getAttribute("user");
        log.info("User : {}", user);

        if (DataUtils.isNullOrEmpty(user)) {
            var url = request.getContextPath() + "/dashboard/login";

            String referer = request.getHeader("Referer");
            if (!DataUtils.isNullOrEmpty(referer)) {
                url += "?redirect=" + referer;
            }

            response.sendRedirect(url);
            return false;
        }

        return true;
    }

}
