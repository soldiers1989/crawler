package com.microservice.dao.repository.crawler.telecom.hebei;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.dao.entity.crawler.telecom.hebei.TelecomHebeiCallRec;

public interface TelecomHebeiCallRecRepository extends JpaRepository<TelecomHebeiCallRec, Long>{
	//勿删   2018年4月8日为统计指定taskid下的通话记录总数而添加的方法
	int countByTaskid(String taskid); 
}
