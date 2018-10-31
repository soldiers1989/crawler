package com.microservice.dao.repository.crawler.housing.guigang;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.dao.entity.crawler.housing.guigang.HousingGuiGangHtml;



@Repository
public interface HousingGuiGangHtmlRepository extends JpaRepository<HousingGuiGangHtml, Long> {

}
