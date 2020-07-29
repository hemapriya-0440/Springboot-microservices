package com.microservices.CurrencyExchangeService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.CurrencyExchangeService.model.ExchangeValue;
import com.microservices.CurrencyExchangeService.repo.ExchangeRepository;

@RestController
public class ExchangeController {
	@Autowired
	private Environment env;
	@Autowired
	private ExchangeRepository repo;
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
public ExchangeValue retrieveExchangevalue(@PathVariable String from,@PathVariable String to) {
		
		ExchangeValue	exchangevalue= repo.findByFromAndTo(from, to);
		exchangevalue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
	return exchangevalue;
}
}
