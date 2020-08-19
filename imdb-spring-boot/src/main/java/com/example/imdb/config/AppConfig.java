package com.example.imdb.config;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.imdb.domain.Genre;
import com.example.imdb.service.MovieService;

@Configuration
public class AppConfig {

	@Bean
	public Collection<Genre> genres(MovieService movieSrv){
		return movieSrv.findAllGenres()
				        .stream()
				        .sorted(Comparator.comparing(Genre::getName))
				        .collect(Collectors.toList());
	}
}
