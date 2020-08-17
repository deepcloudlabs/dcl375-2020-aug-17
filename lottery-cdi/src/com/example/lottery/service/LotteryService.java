package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
	
	public List<List<Integer>> draw(int column){
		return IntStream.range(0, column)
				        .mapToObj( i -> this.draw())
				        .collect(Collectors.toList());
	}
}
