package com.microservice.dao.repository.crawler.pbccrc;


import com.microservice.dao.entity.crawler.pbccrc.PbccrcFlowStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PbccrcFlowStatusRepository extends JpaRepository<PbccrcFlowStatus,Long> {
}
