package com.ftgcar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class AppConfig implements WebMvcConfigurer {

    @Value("${spring.data.rest.allowedOrigins}")
    private String[] allowedOrigins;

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(basePath + "/**").allowedOrigins(allowedOrigins).allowedMethods("GET", "POST", "PUT");
    }
}
