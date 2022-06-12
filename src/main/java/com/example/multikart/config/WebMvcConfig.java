package com.example.multikart.config;

import com.example.multikart.common.interceptor.AdminInterceptor;
import com.example.multikart.common.interceptor.CustomerInterceptor;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private AdminInterceptor adminInterceptor;
    @Autowired
    private CustomerInterceptor customerInterceptor;

    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/",
            "classpath:/resources/",
            "classpath:/static/",
            "classpath:/public/"
    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Loại đi trường hợp /login
        registry.addInterceptor(customerInterceptor)
                .addPathPatterns("/checkout", "/profile**", "/profile/**")
                .excludePathPatterns("/signin", "/register", "/cart");

        // Interceptor này áp dụng cho các URL có dạng /dashboard*
        // Loại đi trường hợp /dashboard/login
        registry.addInterceptor(adminInterceptor)
                .addPathPatterns("/dashboard**", "/dashboard/**")
                .excludePathPatterns("/dashboard/login");
    }

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }

}
