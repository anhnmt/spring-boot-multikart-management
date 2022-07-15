package com.example.multikart.common.interceptor;

import com.example.multikart.common.DataUtils;
import com.example.multikart.common.Utils;
import com.example.multikart.domain.dto.UserDTO;
import com.example.multikart.domain.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
public class AdminInterceptor implements HandlerInterceptor {

    private static final String[] authUrl = {
            "/dashboard/categories/*",
            "/dashboard/roles/*",
            "/dashboard/users/*",
            "/dashboard/units/*",
            "/dashboard/suppliers/*",
            "/dashboard/customers/*",
            "/dashboard/payments/*",
            "/dashboard/transports/*"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        log.info("\n-------- AdminInterceptor.preHandle --- ");
        log.info("Current url: {}", request.getRequestURI());

        var user = (UserDTO) request.getSession().getAttribute("user");
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

        // validate user is admin
        if (!user.getRoleName().equals("ADMIN") && isAuthUrl(request.getRequestURI())) {
            response.sendRedirect(request.getContextPath() + "/dashboard");
            return false;
        }

        return true;
    }

    private boolean isAuthUrl(String url) {
        return Arrays.stream(authUrl).anyMatch(url::matches);
    }

}
