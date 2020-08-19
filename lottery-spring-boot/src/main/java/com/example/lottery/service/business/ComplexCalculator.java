package com.example.lottery.service.business;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "calculator", havingValue = "complex")
public class ComplexCalculator implements Calculator {
	public int add(int x, int y) {
		return x + y + 1;
	}

	public int sub(int x, int y) {
		return x - y - 1;
	}

	public int mul(int x, int y) {
		return x * y + 1;
	}

	public int div(int x, int y) {
		return x / y + 1;
	}
}
