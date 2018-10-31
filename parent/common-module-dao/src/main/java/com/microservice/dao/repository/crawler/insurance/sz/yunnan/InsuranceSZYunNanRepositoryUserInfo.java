package com.microservice.dao.repository.crawler.insurance.sz.yunnan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.insurance.sz.yunnan.InsuranceSZYunNanUserInfo;

@Repository
public interface InsuranceSZYunNanRepositoryUserInfo extends JpaRepository<InsuranceSZYunNanUserInfo,Long>{

}
