package com.example.imdb.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

import com.example.imdb.domain.Genre;
import com.example.imdb.model.CriteriaBean;
import com.example.imdb.service.MovieService;

@Controller
@RequestScope
@RequestMapping("search")
public class ImdbController {
	@Autowired
	private MovieService movieSrv;
	@Autowired
	private Collection<Genre> genres;
	
	@ModelAttribute("genres")
	public Collection<Genre> getGenres() {
		return genres;
	}

	@GetMapping
	public String initPage() {
		return "home";
	}

	@PostMapping
	public String search(Model model, CriteriaBean criteria) {
		var movies = movieSrv.findAllMoviesByCriteria(criteria);
		model.addAttribute("movies", movies);
		return "home";
	}
}
