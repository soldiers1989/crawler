package com.microservice.dao.repository.crawler.insurance.lanzhou;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.insurance.lanzhou.InsuranceLanZhouMedicalInfo;

@Repository
public interface InsuranceLanZhouMedicalInfoRepository extends JpaRepository<InsuranceLanZhouMedicalInfo, Long> {

}
