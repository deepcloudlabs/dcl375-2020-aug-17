package com.example.lottery.service.business;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.lottery.service.RandomNumberService;

@Service
public class SimpleRandomNumberService implements RandomNumberService {

	private Random random = new Random();

	@Override
	public int generate(int min, int max) {
		return random.nextInt(max-min+1)+min;
	}

}
