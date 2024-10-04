package org.zerock.apiserver1.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@Log4j2
public class CustomServletConfig implements WebMvcConfigurer {


    @Override
    public void addFormatters(FormatterRegistry registry) {

        log.info("=------------------");
        log.info("addFormatters");
        WebMvcConfigurer.super.addFormatters(registry);

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .maxAge(500)
                .allowedMethods("GET","POST","PUT","DELETE","HEAD","OPTIONS")
                .allowedOrigins("*");
    }
}
