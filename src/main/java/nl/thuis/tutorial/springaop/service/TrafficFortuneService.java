package nl.thuis.tutorial.springaop.service;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class TrafficFortuneService {
	
	private static Logger logger = Logger.getLogger(TrafficFortuneService.class.getName());

	public String getFortune() {
		
		// Simulate delay
		try {
			TimeUnit.SECONDS.sleep(5);
			logger.info("In the getFortune() method");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "Expect Heavy Traffic!\n";
	}
}
