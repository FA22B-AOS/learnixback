package de.szut.learnixback;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Alle Endpunkte
                .allowedOrigins("*") // Erlaube alle Ursprünge
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // Erlaube alle HTTP-Methoden
    }
}
