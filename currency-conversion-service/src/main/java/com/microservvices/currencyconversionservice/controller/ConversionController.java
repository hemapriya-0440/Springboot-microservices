package com.microservvices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.microservvices.currencyconversionservice.model.ConversionBean;
import com.microservvices.currencyconversionservice.proxy.CurrencyExchangeServiceproxy;

@RestController
public class ConversionController {
	
	RestTemplate resttemplate =new RestTemplate();
	@Autowired
	private CurrencyExchangeServiceproxy proxy;

	@GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
	public ConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to", to);

		ResponseEntity<ConversionBean> responseEntity = resttemplate.getForEntity(
				"http://localhost:8000/currency-exchange/from/{from}/to/{to}", ConversionBean.class,
				uriVariables);

		ConversionBean response = responseEntity.getBody();

		return new ConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
	public ConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {

		ConversionBean response =proxy.retrieveExchangevalue(from, to);
		return new ConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
				quantity.multiply(response.getConversionMultiple()), response.getPort());
	}
}

