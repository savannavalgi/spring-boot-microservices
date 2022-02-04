package com.savan.microservices.limitsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.savan.microservices.limitsservice.bean.Limits;
import com.savan.microservices.limitsservice.configuration.Configuration;

@RestController
public class LimitsController {
	
	@Autowired
	Configuration config;
	
	@GetMapping("/limits")
	public Limits retriveLimits() {
		return new Limits(config.getMin(),config.getMax());
	}
}
