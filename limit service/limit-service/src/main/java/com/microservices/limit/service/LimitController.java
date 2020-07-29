package com.microservices.limit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.limit.config.configuration;
import com.microservices.limit.domain.LimitConfig;


@RestController
public class LimitController {
	@Autowired
	private configuration conf;
	
	@GetMapping("/limit")
public LimitConfig retrievelimit() {
	return  new LimitConfig(conf.getMaximum(), conf.getMinimum());
}
}
