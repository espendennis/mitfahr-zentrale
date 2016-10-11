package com.espen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("*/webjars/**").addResourceLocations("/webjars/");
        registry.addResourceHandler("*/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("*/resources/**").addResourceLocations("/resources/");
    }
}
