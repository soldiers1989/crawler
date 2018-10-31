package com.microservice.dao.repository.crawler.insurance.jingzhou;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.dao.entity.crawler.insurance.jingzhou.InsurancejingzhouShengYuInfo;
/**
 * 济南社保  Repository
 * @author qizhongbin
 *
 */
public interface InsurancejingzhouShengYuInfoRepository extends JpaRepository<InsurancejingzhouShengYuInfo, Long>{
//	List<InsurancejingzhouShengYuInfo> findByTaskid(String taskid);
}
