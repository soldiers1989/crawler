package com.microservice.dao.repository.crawler.insurance.taizhou;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.insurance.taizhou.InsuranceTaiZhouInjury;

@Repository
public interface InsuranceTaiZhouRepositoryInjury extends JpaRepository<InsuranceTaiZhouInjury,Long>{

}
