package com.microservice.dao.repository.crawler.telecom.chongqing;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.telecom.chongqing.TelecomChongqingStarlevel;

@Repository
public interface TelecomChongqingStarlevelRepository extends JpaRepository<TelecomChongqingStarlevel, Long>{
	
}
 