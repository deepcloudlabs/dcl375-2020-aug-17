package com.example.lottery.service.business;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.QualityType;
import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.RandomServiceType;

@Service
@ConfigurationProperties(prefix = "lottery")
@Validated
public class SimpleLotteryService implements LotteryService {
	// Bean Validation (Java EE Specification)
	@Min(1)
	private int min;
	@Max(100)
	private int max;
	@Max(10)
	@Min(1)
	private long size;

	public void setMin(int min) {
		this.min = min;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Autowired
	private ApplicationContext container;

	@Autowired
	@RandomServiceType(QualityType.FAST)
	private RandomNumberService randomNumberService;

	@Autowired
	private List<RandomNumberService> randomNumberServices;

	@Autowired
	private Map<String, RandomNumberService> randomNumberServiceMap;

	private int counter;

	@PostConstruct
	public void init() {
		randomNumberServices.forEach(srv -> System.err.println(srv.getClass().getName()));
		randomNumberServiceMap
				.forEach((label, component) -> System.err.println(label + ": " + component.getClass().getSimpleName()));
		var rndSrvImps = container.getBeansOfType(RandomNumberService.class);
		rndSrvImps
				.forEach((label, component) -> System.err.println(label + ": " + component.getClass().getSimpleName()));

	}

	@Override
	public List<Integer> draw() {
		var rndSrv = randomNumberServices.get(counter++ % randomNumberServices.size());

		return IntStream.generate(() -> rndSrv.generate(min, max)).distinct().limit(size).sorted().boxed()
				.collect(Collectors.toList());
	}

	public List<List<Integer>> draw(int column) {
		return IntStream.range(0, column).mapToObj(i -> this.draw()).collect(Collectors.toList());
	}

}
