package nl.thuis.tutorial.springaop;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import nl.thuis.tutorial.springaop.config.SpringConfig;
import nl.thuis.tutorial.springaop.service.TrafficFortuneService;

public class AroundMainApp {
	
	private static Logger logger = Logger.getLogger(AroundMainApp.class.getName());

	public static void main(String[] args) {

		// read Spring config file
		AnnotationConfigApplicationContext config = new AnnotationConfigApplicationContext(SpringConfig.class);

		// Get bean from context
		TrafficFortuneService trafficFortune = config.getBean("trafficFortuneService", TrafficFortuneService.class);
		logger.info("Start AroundMainApp\n");
		
		// Calling getFortune, give true for exception
		logger.info("Calling normal getFortune()");
		String data = trafficFortune.getFortune();
		logger.info(data + "\n");
		
		// Calling getFortune, give true for exception
		logger.info("Calling exeption getFortune()");
		data = trafficFortune.getFortune(true);
		logger.info(data + "\n");

		// Closing context
		logger.info("Ending AroundMainApp");
		config.close();
	}
}
