package com.example.lottery;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.lottery.service.LotteryService;

public class App {
	public static void main(String[] args) {
		ConfigurableApplicationContext container = 
				new AnnotationConfigApplicationContext("com.example.lottery");
		container.getBean(LotteryService.class)
		         .draw(10)
		         .forEach(System.out::println);
		container.close();
	}
}
