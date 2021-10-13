package com.example.videojuegos;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ImagenConfig implements WebMvcConfigurer {

    public void addResourceHanlders(ResourceHandlerRegistry registry){
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("imagenes/**").addResourceLocations("file:/C:/Videojuegos/imagenes/");
    }

}
