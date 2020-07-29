package com.microservices.CurrencyExchangeService.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.CurrencyExchangeService.model.ExchangeValue;
@Repository
public interface ExchangeRepository extends JpaRepository<ExchangeValue, Long> {
public ExchangeValue findByFromAndTo(String from,String to);
}
