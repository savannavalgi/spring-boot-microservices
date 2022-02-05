package com.savan.microservices.currencyconversionservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	@Autowired
	currencyExchangeServiceProxy proxy;
	
	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable("from") String from,
			@PathVariable("to") String to,@PathVariable("quantity") BigDecimal quantity) {
		
		RestTemplate rs = new RestTemplate();
		CurrencyConversion curConv = rs.getForObject("http://localhost:8000//currency-exchange/from/"+from+"/to/"+to, CurrencyConversion.class);
		
		curConv.setTotalCalculatedAmount(quantity.multiply(curConv.getConversionMultiple()));
		
		
		return curConv;
	}
	
	@GetMapping("/currency-conversion-feigh/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversionFeigh(@PathVariable("from") String from,
			@PathVariable("to") String to,@PathVariable("quantity") BigDecimal quantity) {
		
		
		
		CurrencyConversion curConv = proxy.retriveExchangeValue(from, to);
		
		curConv.setTotalCalculatedAmount(quantity.multiply(curConv.getConversionMultiple()));
		curConv.setEnvironment("from feign");
//		CurrencyConversion response = new CurrencyConversion(1000L,from,to,quantity);
//		response.setTotalCalculatedAmount(quantity.multiply(curConv.getConversionMultiple()));
//		response.setEnvironment(curConv.getEnvironment());
//		response.setConversionMultiple(curConv.getConversionMultiple());
		
		return curConv;
	}
	
	
}
