package com.example.collectibles;

import com.example.collectibles.interceptors.ApplicationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // it is possible to configure the interceptor for only some paths.
        registry.addInterceptor(new ApplicationInterceptor());
    }
}
