package com.microservice.dao.repository.crawler.insurance.haikou;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.insurance.haikou.InsuranceHaiKouUserInfo;

@Repository
public interface InsuranceHaiKouRepositoryUserInfo extends JpaRepository<InsuranceHaiKouUserInfo, Long>{

}
