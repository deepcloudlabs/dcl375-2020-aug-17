package com.example.imdb;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.imdb.config.AppConfig;
import com.example.imdb.service.MovieService;

public class App {
	public static void main(String[] args) {
		ConfigurableApplicationContext container =
				new AnnotationConfigApplicationContext(AppConfig.class);
		var movieSrv = container.getBean(MovieService.class);
		var genre = "Drama";
		var fromYear = 1970;
		var toYear = 1979;
		movieSrv.findAllMoviesByYearRangeAndGenre(genre , fromYear, toYear)
		        .forEach(System.err::println);
		container.getBeansOfType(Object.class)
		         .forEach((label, component) -> System.err.println(String.format("%24s: %s", label, component.getClass().getName())));
		container.close();
	}
}
