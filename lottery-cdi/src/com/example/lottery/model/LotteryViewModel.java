package com.example.lottery.model;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

// Ctrl + Shift + O : Organize Import
// Alt + Shift + S
// POJO: Plain Old Java Object
// Class -> Component -> Application Server 
// Class -> Object : new LotteryViewModel
// Component-based Programming : do not use new!
// Container (Application Server -> TomEE) -> Life Cycle
// Annotation (Java SE 5) -> Declarative Programming
@Named // CDI (Context and Dependency Injection) Component
@RequestScoped // Scope: created when request received, destroyed when response sent
public class LotteryViewModel {
	private List<Integer> numbers; // lottery numbers -> JSP (Java Server Pages)

	public LotteryViewModel() {
		numbers = ThreadLocalRandom.current()
				                   .ints(1,50)
				                   .distinct()
				                   .limit(6)
				                   .sorted()
				                   .boxed()
				                   .collect(Collectors.toList());
	}

	public List<Integer> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}
	
}
