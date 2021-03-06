package com.microservice.dao.repository.crawler.mobile.etl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.dao.entity.crawler.mobile.etl.ProMobileSmsInfo;

public interface ProMobileSmsInfoRepository extends JpaRepository<ProMobileSmsInfo, Long>{

	List<ProMobileSmsInfo> findByTaskId(String taskid);
}
