package com.microservice.dao.repository.crawler.telecom.liaoning;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.telecom.liaoning.TelecomLiaoNingPayThrem;

@Repository
public interface TelecomLiaoNingPayThremResultRepository extends JpaRepository<TelecomLiaoNingPayThrem, Long>{

}
