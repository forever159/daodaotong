package com.hibi.www.config;

import com.hibi.www.interceptor.LoginInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.HashSet;
import java.util.Set;


@Configuration
@ComponentScan("com.hibi.www")
public class MyMvcConfig extends WebMvcConfigurationSupport {
    private static final Logger logger = LoggerFactory.getLogger(MyMvcConfig.class);

    @Autowired
    LoginInterceptor loginInterceptor;



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] modules = new String[]{"/","init/login","login.html","static/**"};
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(modules);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
        super.addResourceHandlers(registry);
    }

    //
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                         .allowedOrigins("*")
                         .allowedHeaders("*/*")
                         .allowedMethods("*")
                         .maxAge(120);
    }

//    @Bean
//    public InternalResourceViewResolver   internalResourceViewResolver(){
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("WEB-INF/classes/views/");
//        resolver.setSuffix(".html");
//        resolver.setViewClass(JstlView.class);
//        return resolver;
//    }



}
