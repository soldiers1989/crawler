package com.microservice.dao.repository.crawler.insurance.panzhihua;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.insurance.panzhihua.InsurancePanZhiHuaShiYeInfo;


@Repository
public interface InsurancePanZhiHuaShiYeInfoRepository extends JpaRepository<InsurancePanZhiHuaShiYeInfo, Long>{

}
