package com.microservvices.currencyconversionservice.proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservvices.currencyconversionservice.model.ConversionBean;

//@FeignClient(name="currency-exchange-service",url="localhost:8000")with out ribbon
//@FeignClient(name="currency-exchange-service")
@FeignClient(name="netflix-zuul-api-server")
@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceproxy {
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	public ConversionBean retrieveExchangevalue(@PathVariable String from,@PathVariable String to) ;
}
