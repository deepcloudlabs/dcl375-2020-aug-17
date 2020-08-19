package com.example.lottery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.model.LotteryViewModel;

@Controller
@RequestScope
@RequestMapping("draw")
public class LotteryController {
	@Autowired
	private LotteryViewModel lotteryViewModel;
	
	// GET http://localhost:8001/lottery/numbers/draw
	// @RequestMapping(method = RequestMethod.GET)
	@GetMapping
	public String landingPage(Model model) {
		// ${lottery.numbers}
		model.addAttribute("lottery", lotteryViewModel);
		return "lottery"; // logical view name -> physical view name
		// "lottery" -> "/WEB-INF/lottery.jsp"
	}
}
