//package com.revature.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public WebMvcConfigurer webMvcConfigurer(){
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**") // Applies CORS settings to all paths
//                        .allowedMethods("GET", "POST", "PUT", "DELETE") // Allows all these HTTP methods
//                        .allowedOrigins("*"); // Allows requests from any origin
//            }
//        };
//    }
//}
