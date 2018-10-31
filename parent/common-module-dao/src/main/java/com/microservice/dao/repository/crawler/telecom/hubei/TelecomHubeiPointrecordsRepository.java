package com.microservice.dao.repository.crawler.telecom.hubei;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.telecom.hubei.TelecomHubeiPointrecords;

@Repository
public interface TelecomHubeiPointrecordsRepository extends JpaRepository<TelecomHubeiPointrecords, Long>{

}
