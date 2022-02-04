package com.savan.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	
	@Autowired
	private Environment env;
	
	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retriveExchangeValue(@PathVariable("from") String from,
			@PathVariable("to") String to) {
		
		ExchangeValue exVal = new ExchangeValue(1000L, "USD", "INR", BigDecimal.valueOf(70));
		exVal.setEnvironment(env.getProperty("local.server.port"));
		return exVal;
	}

}
