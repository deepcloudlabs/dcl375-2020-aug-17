package com.example.lottery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.example.lottery.service.LotteryService;
import com.example.lottery.service.business.Calculator;

// JAR:
// mvn clean install spring-boot:repackage

// Run:
// mvn clean install spring-boot:run

// Window-> Show View-> Other... -> BootDashboard
// Dev Tools
@SpringBootApplication // Component
// Default: @ComponentScan("com.example.lottery")
// /tmp/lottery.properties:
// -Dspring.config.name=lottery
// -Dspring.config.location=c:/tmp
public class LotterySpringBootApplication implements ApplicationRunner {
	@Autowired
	private Calculator calculator;
	@Autowired
	private LotteryService lotteryService;

	public static void main(String[] args) {
		new SpringApplicationBuilder().banner((env, sourceClass, out) -> {
			out.println("DEEP|CLOUD|LABS");
		}).bannerMode(Banner.Mode.CONSOLE).profiles("dev").sources(LotterySpringBootApplication.class).run(args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.err.println("2+2= " + calculator.add(2, 2));
		System.err.println("2*2= " + calculator.mul(2, 2));
		lotteryService.draw(10).forEach(System.err::println);
	}

}
