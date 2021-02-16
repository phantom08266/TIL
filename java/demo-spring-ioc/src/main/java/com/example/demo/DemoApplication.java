package com.example.demo;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(DemoApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
//        SpringApplication.run(DemoApplication.class, args);
    }

//    @Bean
//    public MessageSource messageSource(){
//        ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
//        reloadableResourceBundleMessageSource.setBasename("classpath:/messages");
//        reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
//        reloadableResourceBundleMessageSource.setCacheSeconds(3);
//        return reloadableResourceBundleMessageSource;
//    }
}
