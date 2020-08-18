package com.example.lottery.service.business;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.RandomNumberService;

@Service
public class SimpleLotteryService implements LotteryService {
    @Autowired
    private RandomNumberService randomNumberService;
    
	@Override
	public List<Integer> draw() {
		return IntStream.generate(() -> randomNumberService.generate(1, 50))
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
