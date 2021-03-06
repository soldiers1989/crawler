package com.microservice.dao.repository.crawler.housing.shijiazhuang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.housing.shijiazhuang.HousingShiJiaZhuangHtml;

/**
 * @description:
 * @author: sln 
 * @date: 2017年10月19日 下午2:46:45 
 */
@Repository
public interface HousingShiJiaZhuangHtmlRepository extends JpaRepository<HousingShiJiaZhuangHtml, Long> {

}
