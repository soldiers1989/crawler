package com.microservice.dao.repository.crawler.mobile.etl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.dao.entity.crawler.mobile.etl.MobileReportContactsStatisticsThreeMonth;

public interface MobileReportContactsStatisticsThreeMonthRepository extends JpaRepository<MobileReportContactsStatisticsThreeMonth, Long>{

	List<MobileReportContactsStatisticsThreeMonth> findByTaskId(String taskid);
}
