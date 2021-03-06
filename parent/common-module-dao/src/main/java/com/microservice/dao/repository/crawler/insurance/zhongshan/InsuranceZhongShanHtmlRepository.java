package com.microservice.dao.repository.crawler.insurance.zhongshan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.insurance.zhongshan.InsuranceZhongShanHtml;

@Repository
public interface InsuranceZhongShanHtmlRepository extends JpaRepository<InsuranceZhongShanHtml, Long> {
}
