package com.espen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

/**
 * Spring Boor main Application
 * 
 * @author Dennis
 *
 */

@SpringBootApplication
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
		
	}
	
	@Bean
	public UrlBasedViewResolver tilesViewResolver() {

		UrlBasedViewResolver tilesViewResolver = new UrlBasedViewResolver();
		tilesViewResolver.setViewClass(TilesView.class);
		return tilesViewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {

		TilesConfigurer tconf = new TilesConfigurer();
		tconf.setDefinitions(new String[] { "/WEB-INF/tiles/layout/tiles.xml" });
		return tconf;

	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("*/webjars/**").addResourceLocations("/webjars/");
        registry.addResourceHandler("*/resources/**").addResourceLocations("/resources/");
        registry.addResourceHandler("*/resources/**").addResourceLocations("/resources/");
    }
}
