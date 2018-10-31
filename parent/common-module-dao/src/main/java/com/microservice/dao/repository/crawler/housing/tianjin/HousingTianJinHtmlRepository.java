package com.microservice.dao.repository.crawler.housing.tianjin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.housing.tianjin.HousingTianJinHtml;

/**
 * @description:
 * @author: sln 
 * @date: 2017年9月29日 上午10:28:43 
 */
@Repository
public interface HousingTianJinHtmlRepository extends JpaRepository<HousingTianJinHtml, Long> {

	HousingTianJinHtml findByTaskid(String taskid);
	
}
