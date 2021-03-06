package app.service.common;


import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import com.crawler.mobile.json.MessageLogin;
import com.google.gson.Gson;
import com.microservice.dao.entity.crawler.mobile.TaskMobile;
import com.microservice.dao.entity.crawler.telecom.common.TelecomCommonHtml;
import com.microservice.dao.entity.crawler.telecom.common.TelecomCommonPointsAndCharges;
import com.microservice.dao.repository.crawler.mobile.TaskMobileRepository;
import com.microservice.dao.repository.crawler.telecom.common.TelecomCommonHtmlRepository;
import com.microservice.dao.repository.crawler.telecom.common.TelecomCommonPointsAndChargesRepository;
import com.microservice.dao.repository.crawler.telecom.common.TelecomCommonStarlevelRepository;

import app.commontracerlog.TracerLog;
import app.service.ChaoJiYingOcrService;
import app.service.CrawlerStatusMobileService;

@Component
@EnableAsync
@EntityScan(basePackages = "com.microservice.dao.entity.crawler.telecom.common")
@EnableJpaRepositories(basePackages = "com.microservice.dao.repository.crawler.telecom.common")
public  class TelecomBasicService {

	@Autowired
	private AgentService agentService;
	
	@Autowired
	protected TaskMobileRepository taskMobileRepository;
	
	@Autowired
	protected ChaoJiYingOcrService chaoJiYingOcrService;
	

	@Autowired
	protected TracerLog tracerLog;

	@Autowired
	protected CrawlerStatusMobileService crawlerStatusMobileService;

	@Autowired
	protected TelecomCommonPointsAndChargesRepository telecomCommonPointsAndChargesRepository;

	@Autowired
	protected TelecomCommonStarlevelRepository telecomCommonStarlevelRepository;

	@Autowired
	protected TelecomCommonHtmlRepository telecomCommonHtmlRepository;	
	
	protected  Gson gs = new Gson();
	
	protected void save(TaskMobile taskMobile) {
		taskMobileRepository.save(taskMobile);
	}

	protected TaskMobile findtaskMobile(String taskid) {
		return taskMobileRepository.findByTaskid(taskid);
	}

	protected void save(TelecomCommonPointsAndCharges result) {
		telecomCommonPointsAndChargesRepository.save(result);
	}
	
	protected void save(TelecomCommonHtml result) {
		telecomCommonHtmlRepository.save(result);
	}
	
	protected void quit(MessageLogin messageLogin,WebDriver driver) {
		driver.close();
		try{
			agentService.releaseInstance(messageLogin.getIp(), driver);

		}catch(Exception e){
			e.printStackTrace();
		}
	}


}