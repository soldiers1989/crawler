package com.microservice.dao.repository.crawler.e_commerce.etl.jd;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.dao.entity.crawler.e_commerce.etl.jd.AuthInfoJD;


public interface AuthInfoJDRepository extends JpaRepository<AuthInfoJD, Long>{

	List<AuthInfoJD> findByTaskId(String taskid);

}
