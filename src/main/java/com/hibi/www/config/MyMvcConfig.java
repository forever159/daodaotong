package com.hibi.www.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class MyMvcConfig extends WebMvcConfigurationSupport {
    private static final Logger logger = LoggerFactory.getLogger(MyMvcConfig.class);

//    @Autowired
//    LoginInterceptor loginInterceptor;



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
