package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.crawler.housingfund.json.MessageLoginForHousing;
import com.microservice.dao.entity.crawler.housing.basic.TaskHousing;

import app.service.common.HousingBasicService;
import app.service.common.aop.ICrawlerLogin;

@Component
@Service
@EnableAsync
@EntityScan(basePackages = "com.microservice.dao.entity.crawler.housing.tonghua")
@EnableJpaRepositories(basePackages = "com.microservice.dao.repository.crawler.housing.tonghua")
public class HousingFundTongHuaCommonService extends HousingBasicService implements ICrawlerLogin{

	@Autowired
	private HousingFundTongHuaService housingFundTongHuaService;
	
	@Async
	@Override
	public TaskHousing login(MessageLoginForHousing messageLoginForHousing) {
		TaskHousing taskHousing = taskHousingRepository.findByTaskid(messageLoginForHousing.getTask_id());
		housingFundTongHuaService.login(messageLoginForHousing,taskHousing);
		return taskHousing;
		
	}
	@Async
	@Override
	public TaskHousing getAllData(MessageLoginForHousing messageLoginForHousing) {
		TaskHousing taskHousing = taskHousingRepository.findByTaskid(messageLoginForHousing.getTask_id());
		housingFundTongHuaService.crawlerUserInfo(messageLoginForHousing,taskHousing);
		housingFundTongHuaService.crawlerAccount(messageLoginForHousing,taskHousing);
		return taskHousing;
	}

	@Override
	public TaskHousing getAllDataDone(String taskId) {
		// TODO Auto-generated method stub
		return null;
	}


}
