package com.microservice.dao.repository.crawler.insurance.jinan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.dao.entity.crawler.insurance.jinan.InsuranceJiNanYanglaoInfo;
/**
 * 济南社保  Repository
 * @author qizhongbin
 *
 */
public interface InsuranceJiNanYanglaoInfoRepository extends JpaRepository<InsuranceJiNanYanglaoInfo, Long>{
	List<InsuranceJiNanYanglaoInfo> findByTaskid(String taskid);
}
