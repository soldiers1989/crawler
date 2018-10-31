package com.microservice.dao.repository.crawler.housing.guangzhou;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.housing.guangzhou.HousingGuangzhouDetailAccount;

/**
 * @description:
 * @author: sln 
 * @date: 2017年9月29日 上午10:27:34 
 */
@Repository
public interface HousingGuangzhouDetailAccountRepository extends JpaRepository<HousingGuangzhouDetailAccount, Long> {

}
