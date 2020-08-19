package com.example.imdb;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.imdb.config.AppConfig;
import com.example.imdb.config.CacheConfig;
import com.example.imdb.service.MovieService;

public class CacheExample {

	public static void main(String[] args) {
		ConfigurableApplicationContext container =
				new AnnotationConfigApplicationContext(AppConfig.class,CacheConfig.class);
		var movieSrv = container.getBean(MovieService.class);
		movieSrv.findAllGenres(); // first time
		movieSrv.findAllGenres();
		movieSrv.findAllGenres();
		movieSrv.findAllGenres();
		movieSrv.findAllGenres();
		movieSrv.findAllGenres();
		movieSrv.findAllGenres();
		movieSrv.findAllGenres();
		movieSrv.findAllGenres();
		movieSrv.findAllGenres();
		container.close();
	}

}
