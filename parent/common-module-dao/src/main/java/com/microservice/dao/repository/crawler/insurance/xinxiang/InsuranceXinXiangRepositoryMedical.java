package com.microservice.dao.repository.crawler.insurance.xinxiang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.insurance.xinxiang.InsuranceXinXiangMedical;

@Repository
public interface InsuranceXinXiangRepositoryMedical extends JpaRepository<InsuranceXinXiangMedical, Long>{

}
