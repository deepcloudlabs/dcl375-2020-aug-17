package com.example.world.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

import com.example.world.dao.CountryDao;

@Controller
@RequestScope
@RequestMapping("list")
public class WorldController {
	@Autowired
	private CountryDao countryDao;

	@ModelAttribute("continents")
	public List<String> getContinents() {
		return countryDao.getAllContinents().stream().sorted().collect(Collectors.toList());
	}

	@GetMapping
	public String home() {
		return "list";
	}

	@PostMapping
	public String list(Model model, String continent) {
		var countries = countryDao.findCountriesByContinent(continent);
		model.addAttribute("countries", countries);
		return "list";
	}
}
