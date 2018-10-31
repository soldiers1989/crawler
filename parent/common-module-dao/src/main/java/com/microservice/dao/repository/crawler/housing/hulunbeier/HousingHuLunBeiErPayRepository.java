package com.microservice.dao.repository.crawler.housing.hulunbeier;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.housing.hulunbeier.HousingHuLunBeiErPay;

@Repository
public interface HousingHuLunBeiErPayRepository extends JpaRepository<HousingHuLunBeiErPay, Long>{

}
