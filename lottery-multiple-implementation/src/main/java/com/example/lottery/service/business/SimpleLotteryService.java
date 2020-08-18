package com.example.lottery.service.business;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.QualityType;
import com.example.lottery.service.RandomNumberService;
import com.example.lottery.service.RandomServiceType;

@Service
public class SimpleLotteryService implements LotteryService {
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

	@Value("${lottery.min}") 
	private int lotteryMin;

	@Value("${lottery.max}")
	private int lotteryMax;

	// @Value("#{calculator.add(10,10)}") // SpEL: SPring Expression Language
	@Value("${lottery.size}")
	private long lotterySize;
    
    @PostConstruct
    public void init() {
    	randomNumberServices.forEach(srv -> System.err.println(srv.getClass().getName()));
    	randomNumberServiceMap.forEach(
    			(label, component) -> System.err.println(label+": " + component.getClass().getSimpleName()));
        var rndSrvImps = container.getBeansOfType(RandomNumberService.class);
        rndSrvImps.forEach(
        		(label, component) -> System.err.println(label+": " + component.getClass().getSimpleName()));
        
    }
    
	@Override
	public List<Integer> draw() {
		var rndSrv = randomNumberServices.get(counter++ % randomNumberServices.size());

		return IntStream.generate(() -> rndSrv.generate(lotteryMin, lotteryMax))
				     .distinct()
				     .limit(lotterySize)
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
