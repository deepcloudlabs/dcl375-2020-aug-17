package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class LotteryService {

	public List<Integer> draw(){
		return ThreadLocalRandom.current()
                .ints(1,50)
                .distinct()
                .limit(6)
                .sorted()
                .boxed()
                .collect(Collectors.toList());
	}
}
