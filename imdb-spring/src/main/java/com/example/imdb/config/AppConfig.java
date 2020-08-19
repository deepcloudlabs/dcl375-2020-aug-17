package com.example.imdb.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;

import com.example.imdb.domain.Genre;
import com.example.imdb.service.MovieService;
import com.example.imdb.service.SequenceService;
import com.example.imdb.service.business.InMemoryMovieService;

// MicroService -> DDD -> Hexagonal Architecture -> Application Class (POJO) -> @Configuration, @Bean
@Configuration
@ComponentScan("com.example.imdb")
@EnableAspectJAutoProxy
public class AppConfig {
	@Autowired
	private SequenceService sequenceSrv;

	// Factory Method (GoF)
	@Bean("movie-service")
	@Scope("singleton")
	public MovieService createMovieService() {
		var movieSrv = new InMemoryMovieService();
		movieSrv.setSequenceSrv(this.sequenceSrv);
		movieSrv.populate();
		return movieSrv;
	}

	//@Bean("genres")
	public Collection<Genre> allGenres(MovieService movieSrv) {
		return movieSrv.findAllGenres();
	}
	
//  Preferred Approach:	
//	@Bean
//	@Scope("singleton")
//	public MovieService createMovieService(SequenceService sequenceSrv) {
//		var movieSrv = new InMemoryMovieService();
//		movieSrv.setSequenceSrv(sequenceSrv);
//		movieSrv.populate();
//		return movieSrv;
//	}
}
