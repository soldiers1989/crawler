package com.microservice.dao.repository.crawler.telecom.shanxi1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.telecom.shanxi1.TelecomShanxi1Order;

@Repository
public interface TelecomShanxi1OrderRepository extends JpaRepository<TelecomShanxi1Order, Long>{
	
}
 