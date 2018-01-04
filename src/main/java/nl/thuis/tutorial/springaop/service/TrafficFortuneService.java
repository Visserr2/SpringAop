package nl.thuis.tutorial.springaop.service;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class TrafficFortuneService {

	public String getFortune() {
		
		// Simulate delay
		try {
			TimeUnit.SECONDS.sleep(5);
			System.out.println("In the getFortune() method");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "Expect Heavy Traffic!";
	}
}
