package com.microservice.dao.repository.crawler.insurance.jingzhou;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.dao.entity.crawler.insurance.jingzhou.InsurancejingzhouBaseInfo;

/**
 * 济南社保  Repository
 * @author qizhongbin
 *
 */
public interface InsurancejingzhouBaseInfoRepository extends JpaRepository<InsurancejingzhouBaseInfo, Long>{
	//List<InsurancejingzhouBaseInfo> findByTaskid(String taskid);
}
