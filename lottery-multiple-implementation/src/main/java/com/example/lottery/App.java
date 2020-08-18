package com.example.lottery;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.lottery.service.LotteryService;

// set lottery.min=10 
//-Dlottery.min=10
//-Dspring.profiles.active=test
//-Dspring.profiles.active=dev
//-Dspring.profiles.active=prod
public class App {
	public static void main(String[] args) {
		ConfigurableApplicationContext container = new AnnotationConfigApplicationContext("com.example.lottery.config");
		container.getBean(LotteryService.class).draw(10).forEach(System.out::println);
		container.close();
	}
}
