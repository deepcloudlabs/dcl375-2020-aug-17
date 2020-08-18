package com.example.lottery.service.business;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.example.lottery.service.QualityType;
import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.RandomServiceType;

@Service
@RandomServiceType(QualityType.FAST)
public class FastRandomNumberService implements RandomNumberService {

	@Override
	public int generate(int min, int max) {
		System.err.println("FastRandomNumberService::generate");
		return ThreadLocalRandom.current().nextInt(min, max);
	}

}
