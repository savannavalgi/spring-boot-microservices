package com.savan.microservices.currencyexchangeservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/who")
//	@Retry(name = "WHO", fallbackMethod="hardcoded")
	@CircuitBreaker(name = "WHO", fallbackMethod="hardcoded")
	public String returnHello() {
	
		logger.info("---------------received request-------------- / who ");
		
		String res = new RestTemplate().getForObject("http://localhost:8111/return", String.class);
		
		return res;
	}
	
	public String hardcoded(Exception ex) {
		return "fall back";
	}

}
