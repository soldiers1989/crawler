package com.microservice.dao.repository.crawler.telecom.guangxi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.telecom.guangxi.TelecomGuangxiCall;
@Repository
public interface TelecomGuangxiRepositoryCall  extends JpaRepository<TelecomGuangxiCall,Long>{
	//勿删   2018年4月8日为统计指定taskid下的通话记录总数而添加的方法
	int countByTaskid(String taskid); 
}
