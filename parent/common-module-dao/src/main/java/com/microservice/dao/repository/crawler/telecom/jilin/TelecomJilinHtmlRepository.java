package com.microservice.dao.repository.crawler.telecom.jilin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.telecom.jilin.TelecomJilinHtml;

@Repository
public interface TelecomJilinHtmlRepository extends JpaRepository<TelecomJilinHtml, Long>{

}
 