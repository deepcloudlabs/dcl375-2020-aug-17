package com.example.lottery.service.business;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.lottery.service.QualityType;
import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.RandomServiceType;

@Service
@RandomServiceType(QualityType.SIMPLE)
public class SimpleRandomNumberService implements RandomNumberService {

	private Random random = new Random();

	@Override
	public int generate(int min, int max) {
		System.err.println("SimpleRandomNumberService::generate");
		return random.nextInt(max-min+1)+min;
	}

}
