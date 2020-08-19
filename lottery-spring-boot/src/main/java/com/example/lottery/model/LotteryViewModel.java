package com.example.lottery.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SuppressWarnings("serial")
@Component
@SessionScope
public class LotteryViewModel implements Serializable {

	private List<List<Integer>> numbers; // lottery numbers -> JSP (Java Server Pages) -> HTML

	public LotteryViewModel() {
		numbers = new ArrayList<>();
	}

	public List<List<Integer>> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<List<Integer>> numbers) {
		this.numbers = numbers;
	}

}
