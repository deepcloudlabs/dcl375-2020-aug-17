package com.example.world;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.world.dao.WorldDao;

// Component Definition
// CDI    : @Named
// Spring : @Component, @Service, @Repository

// Scope Definition
// CDI    : @Singleton, @RequestScoped, @SessionScoped, ...
// Spring : @Scope

// Ctrl+Shift+F
// Ctrl+M
public class App {
	public static void main(String[] args) {
		ConfigurableApplicationContext container = new AnnotationConfigApplicationContext("com.example.world");
		System.err.println("Container is ready to serve!");
//		container.getBean(WorldDao.class);
//		container.getBean(CountryDao.class);
//		container.getBean(CityDao.class);
		// List Asian Countries
		var worldDao = container.getBean(WorldDao.class);
		var continent = "Asia";
		worldDao.findCountriesByContinent(continent).forEach(System.err::println);
		container.close();
	}
}
