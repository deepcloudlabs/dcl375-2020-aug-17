package com.example.lottery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.RequestScope;

import com.example.lottery.model.LotteryViewModel;
import com.example.lottery.service.LotteryService;

@Controller
@RequestScope
public class LotteryController {
	@Autowired
	private LotteryViewModel lotteryViewModel;
	@Autowired
	private LotteryService lotteryService;

	// ${lottery.numbers}
	// model.addAttribute("lottery", lotteryViewModel)
	@ModelAttribute("lottery")
	public LotteryViewModel getLotteryViewModel() {
		return lotteryViewModel;
	}

	// GET http://localhost:8001/lottery/numbers/draw
	// @RequestMapping(method = RequestMethod.GET)
	@GetMapping("draw")
	public String landingPage() {
		return "lottery"; // logical view name -> physical view name
		// "lottery" -> "/WEB-INF/lottery.jsp"
	}

	@PostMapping("draw")
	public String draw(@RequestParam(name = "column") int col) {
		lotteryViewModel.getNumbers().addAll(lotteryService.draw(col));
		return "lottery";
	}

	@PostMapping("reset")
	public String reset() {
		lotteryViewModel.getNumbers().clear();
		return "lottery";
	}
}
