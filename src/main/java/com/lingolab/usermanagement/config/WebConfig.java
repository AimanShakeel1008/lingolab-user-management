//package com.lingolab.usermanagement.config;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**") // This allows configuring CORS for all paths
//                .allowedOrigins("http://localhost:3000") // Allow frontend origin; adjust as necessary
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Specify methods to allow
//                .allowedHeaders("*") // Allow all headers
//                .allowCredentials(true); // If needed, especially in sessions
//    }
//}
