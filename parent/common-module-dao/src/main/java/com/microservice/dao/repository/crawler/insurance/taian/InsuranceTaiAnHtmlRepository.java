package com.microservice.dao.repository.crawler.insurance.taian;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.insurance.taian.InsuranceTaiAnHtml;


@Repository
public interface InsuranceTaiAnHtmlRepository extends JpaRepository<InsuranceTaiAnHtml, Long> {

}
