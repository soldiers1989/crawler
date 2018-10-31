package com.microservice.dao.repository.crawler.insurance.shaoguan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.insurance.shaoguan.InsuranceShaoGuanEndowment;
import com.microservice.dao.entity.crawler.insurance.shaoguan.InsuranceShaoGuanHtml;

@Repository
public interface InsuranceShaoGuanRepositoryEndowment extends JpaRepository<InsuranceShaoGuanEndowment, Long>{

}
