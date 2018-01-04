package nl.thuis.tutorial.springaop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import nl.thuis.tutorial.springaop.config.SpringConfig;
import nl.thuis.tutorial.springaop.service.TrafficFortuneService;

public class AroundMainApp {

	public static void main(String[] args) {

		// read Spring config file
		AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(SpringConfig.class);

		// Get bean from context
		TrafficFortuneService trafficFortune = config.getBean("trafficFortuneService", TrafficFortuneService.class);
		System.out.println("Start AroundMainApp\n");
		
		// Calling getFortune
		String data = trafficFortune.getFortune();
		System.out.println(data);

		// Closing context
		System.out.println("\nEnding AroundMainApp");
		config.close();
	}
}
