package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.crawler.housingfund.json.MessageLoginForHousing;
import com.microservice.dao.entity.crawler.housing.basic.TaskHousing;
import com.microservice.dao.entity.crawler.housing.zhaotong.HousingZhaoTongBase;
import com.microservice.dao.entity.crawler.housing.zhaotong.HousingZhaoTongHtml;
import com.microservice.dao.repository.crawler.housing.zhaotong.HousingZhaotongBaseRepository;
import com.microservice.dao.repository.crawler.housing.zhaotong.HousingZhaotongHtmlRepository;

import app.common.WebParam;
import app.parser.HousingFundZhaoTongParser;
import app.service.common.HousingBasicService;

@Component
@Service
@EnableAsync
@EntityScan(basePackages = "com.microservice.dao.entity.crawler.housing.zhaotong")
@EnableJpaRepositories(basePackages = "com.microservice.dao.repository.crawler.housing.zhaotong")
public class HousingFundZhaoTongCommonService extends HousingBasicService{

	
	@Autowired
	private HousingFundZhaoTongParser housingFundZhaoTongParser;
	@Autowired
	private HousingZhaotongBaseRepository baseRepository;
	@Autowired
	private HousingZhaotongHtmlRepository htmlRepository;



	public void crawler(MessageLoginForHousing messageLoginForHousing, TaskHousing taskHousing) {
		try {
			WebParam<HousingZhaoTongBase> webParam = housingFundZhaoTongParser.crawler(messageLoginForHousing,taskHousing);
			if(null!=webParam.getHtml()){
				tracer.addTag("dalibaizu.action.getDetail.SUCCESS", taskHousing.getTaskid());
				updateUserInfoStatusByTaskid("【个人基本信息】采集完成！", 200, taskHousing.getTaskid());
				updatePayStatusByTaskid("【缴费明细信息】已采集完成！", 201, taskHousing.getTaskid());
				HousingZhaoTongHtml  html = new HousingZhaoTongHtml();
				html.setHtml(webParam.getHtml());
				html.setTaskid(messageLoginForHousing.getTask_id());
				html.setUrl(webParam.getUrl());
				htmlRepository.save(html);
				baseRepository.save(webParam.getZhaotongBase());
				updateTaskHousing(taskHousing.getTaskid());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private TaskHousing getUserinfo(TaskHousing taskHousing, String html) {
		try {
			WebParam base = housingFundZhaoTongParser.getUserInfo(html,taskHousing);
			baseRepository.save(base.getZhaotongBase());
		} catch (Exception e) {
		}
		return null;
	}
	

}
