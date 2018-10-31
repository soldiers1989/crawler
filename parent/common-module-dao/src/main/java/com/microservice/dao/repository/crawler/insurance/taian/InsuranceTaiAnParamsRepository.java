/**
 * 
 */
package com.microservice.dao.repository.crawler.insurance.taian;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.insurance.taian.InsuranceTaiAnParams;

/**
 * @author sln
 * @date 2018年8月3日下午7:17:50
 * @Description: 
 */
@Repository
public interface InsuranceTaiAnParamsRepository extends JpaRepository<InsuranceTaiAnParams, Long> {
	InsuranceTaiAnParams findTopByTaskidOrderByCreatetimeDesc(String taskid);
}
