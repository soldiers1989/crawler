package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.crawler.housingfund.json.HousingfundStatusCodeEnum;
import com.crawler.housingfund.json.MessageLoginForHousing;
import com.crawler.microservice.unit.CommonUnit;
import com.microservice.dao.entity.crawler.housing.basic.TaskHousing;
import com.microservice.dao.entity.crawler.housing.tonghua.HousingFundTongHuaHtml;
import com.microservice.dao.entity.crawler.housing.zhumadian.HousingFundZhuMaDianAccount;
import com.microservice.dao.entity.crawler.housing.zhumadian.HousingFundZhuMaDianHtml;
import com.microservice.dao.entity.crawler.housing.zhumadian.HousingFundZhuMaDianUserInfo;
import com.microservice.dao.repository.crawler.housing.zhumadian.HousingFundZhuMaDianRepositoryAccount;
import com.microservice.dao.repository.crawler.housing.zhumadian.HousingFundZhuMaDianRepositoryHtml;
import com.microservice.dao.repository.crawler.housing.zhumadian.HousingFundZhuMaDianRepositoryUserInfo;

import app.common.WebParam;
import app.parser.HousingFundZhuMaDianParser;
import app.service.common.HousingBasicService;
@Component
@Service
@EnableAsync
@EntityScan(basePackages = "com.microservice.dao.entity.crawler.housing.zhumadian")
@EnableJpaRepositories(basePackages = "com.microservice.dao.repository.crawler.housing.zhumadian")
public class HousingFundZhuMaDianService extends HousingBasicService{

	@Autowired
	private HousingFundZhuMaDianParser housingFundZhuMaDianParser;
	@Autowired
	private HousingFundZhuMaDianRepositoryUserInfo housingFundZhuMaDianRepositoryUserInfo;
	@Autowired
	private HousingFundZhuMaDianRepositoryAccount housingFundZhuMaDianRepositoryAccount;
	@Autowired
	private HousingFundZhuMaDianRepositoryHtml housingFundZhuMaDianRepositoryHtml;
	public void login(MessageLoginForHousing messageLoginForHousing, TaskHousing taskHousing) {
		try {
			WebParam webParam = housingFundZhuMaDianParser.login(messageLoginForHousing,taskHousing);
			if(null != webParam)
			{
				if(webParam.getHtml().contains("个人业务"))
				{
					tracer.addTag("action.login.SUCCESS", taskHousing.getTaskid());
					taskHousing.setPhase(HousingfundStatusCodeEnum.HOUSING_LOGIN_SUCCESS.getPhase());
					taskHousing.setPhase_status(HousingfundStatusCodeEnum.HOUSING_LOGIN_SUCCESS.getPhasestatus());
					taskHousing.setDescription(HousingfundStatusCodeEnum.HOUSING_LOGIN_SUCCESS.getDescription());
					taskHousing.setPassword(messageLoginForHousing.getPassword().trim());
					String cookieString = CommonUnit.transcookieToJson(webParam.getWebClient());
					taskHousing.setCookies(cookieString);
					taskHousing.setLogintype(messageLoginForHousing.getLogintype());
					save(taskHousing);
				}
				else
				{
					tracer.addTag("action.login.ERROR1", taskHousing.getTaskid());
					taskHousing.setPhase(HousingfundStatusCodeEnum.HOUSING_LOGIN_ERROR.getPhase());
					taskHousing.setPhase_status("FAIL");
					taskHousing.setDescription(HousingfundStatusCodeEnum.HOUSING_LOGIN_ERROR.getDescription());
					taskHousing.setError_code(HousingfundStatusCodeEnum.HOUSING_LOGIN_ERROR.getError_code());
					taskHousing.setPassword(messageLoginForHousing.getPassword().trim());
					save(taskHousing);
				}
			}
			else
			{
				tracer.addTag("action.login.ERROR2", taskHousing.getTaskid());
				taskHousing.setPhase(HousingfundStatusCodeEnum.HOUSING_LOGIN_ERROR.getPhase());
				taskHousing.setPhase_status("FAIL");
				taskHousing.setDescription(HousingfundStatusCodeEnum.HOUSING_LOGIN_ERROR.getDescription());
				taskHousing.setError_code(HousingfundStatusCodeEnum.HOUSING_LOGIN_ERROR.getError_code());
				taskHousing.setPassword(messageLoginForHousing.getPassword().trim());
				save(taskHousing);
			}
		} catch (Exception e) {
			tracer.addTag("action.login.ERROR.TIMEOUT2", taskHousing.getTaskid());
			taskHousing.setPhase(HousingfundStatusCodeEnum.HOUSING_LOGIN_ERROR.getPhase());
			taskHousing.setPhase_status("FAIL");
			taskHousing.setDescription("登陆超时，请核对信息及其网络");
			taskHousing.setPassword(messageLoginForHousing.getPassword().trim());
			save(taskHousing);
			e.printStackTrace();
		}
		
	}

	public void crawlerUserInfo(MessageLoginForHousing messageLoginForHousing, TaskHousing taskHousing) {
		try {
			WebParam<HousingFundZhuMaDianUserInfo> webParam = housingFundZhuMaDianParser.crawlerUserInfo(messageLoginForHousing,taskHousing);
			if(null != webParam)
			{
				if(null != webParam.getHousingFundZhuMaDianUserInfo())
				{
					tracer.addTag("action.crawlerUserInfo.SUCCESS", taskHousing.getTaskid());
					HousingFundZhuMaDianHtml h = new HousingFundZhuMaDianHtml();
					h.setHtml(webParam.getHtml());
					h.setTaskid(taskHousing.getTaskid());
					h.setType("crawlerUserInfo");
					h.setPagenumber(1);
					h.setUrl(webParam.getUrl());
					housingFundZhuMaDianRepositoryHtml.save(h);
					housingFundZhuMaDianRepositoryUserInfo.save(webParam.getHousingFundZhuMaDianUserInfo());
					taskHousing.setPhase(HousingfundStatusCodeEnum.HOUSING_CRAWLER_USER_MSG_SUCCESS.getPhase());
					taskHousing.setPhase_status(HousingfundStatusCodeEnum.HOUSING_CRAWLER_USER_MSG_SUCCESS.getPhasestatus());
					taskHousing.setDescription(HousingfundStatusCodeEnum.HOUSING_CRAWLER_USER_MSG_SUCCESS.getDescription());
					taskHousing.setUserinfoStatus(200);
					taskHousingRepository.save(taskHousing);
					updateTaskHousing(taskHousing.getTaskid());	
				}
				else
				{
					tracer.addTag("action.crawlerUserInfo.ERROR1", taskHousing.getTaskid());
					taskHousing.setPhase(HousingfundStatusCodeEnum.HOUSING_CRAWLER_USER_MSG_SUCCESS.getPhase());
					taskHousing.setPhase_status(HousingfundStatusCodeEnum.HOUSING_CRAWLER_USER_MSG_SUCCESS.getPhasestatus());
					taskHousing.setDescription(HousingfundStatusCodeEnum.HOUSING_CRAWLER_USER_MSG_SUCCESS.getDescription());
					taskHousing.setUserinfoStatus(201);
					taskHousingRepository.save(taskHousing);
					updateTaskHousing(taskHousing.getTaskid());
				}
			}
			else
			{
				tracer.addTag("action.crawlerUserInfo.ERROR2", taskHousing.getTaskid());
				taskHousing.setPhase(HousingfundStatusCodeEnum.HOUSING_CRAWLER_USER_MSG_SUCCESS.getPhase());
				taskHousing.setPhase_status(HousingfundStatusCodeEnum.HOUSING_CRAWLER_USER_MSG_SUCCESS.getPhasestatus());
				taskHousing.setDescription(HousingfundStatusCodeEnum.HOUSING_CRAWLER_USER_MSG_SUCCESS.getDescription());
				taskHousing.setUserinfoStatus(201);
				taskHousingRepository.save(taskHousing);
				updateTaskHousing(taskHousing.getTaskid());
			}
		} catch (Exception e) {
			tracer.addTag("action.crawlerUserInfo.TIMEOUT", taskHousing.getTaskid());
			taskHousing.setPhase(HousingfundStatusCodeEnum.HOUSING_CRAWLER_USER_MSG_SUCCESS.getPhase());
			taskHousing.setPhase_status(HousingfundStatusCodeEnum.HOUSING_CRAWLER_USER_MSG_SUCCESS.getPhasestatus());
			taskHousing.setDescription(HousingfundStatusCodeEnum.HOUSING_CRAWLER_USER_MSG_SUCCESS.getDescription());
			taskHousing.setUserinfoStatus(201);
			taskHousingRepository.save(taskHousing);
			updateTaskHousing(taskHousing.getTaskid());
			e.printStackTrace();
		}
		
	}

	public void crawlerAccount(MessageLoginForHousing messageLoginForHousing, TaskHousing taskHousing) {
		try {
			WebParam<HousingFundZhuMaDianAccount> webParam = housingFundZhuMaDianParser.crawlerAccount(messageLoginForHousing,taskHousing);
			if(null != webParam)
			{
				if(webParam.getList() != null)
				{
					tracer.addTag("action.crawlerAccount.SUCCESS", taskHousing.getTaskid());
					HousingFundZhuMaDianHtml h = new HousingFundZhuMaDianHtml();
					h.setHtml(webParam.getHtml());
					h.setTaskid(taskHousing.getTaskid());
					h.setType("crawlerAccount");
					h.setPagenumber(1);
					h.setUrl(webParam.getUrl());
					housingFundZhuMaDianRepositoryHtml.save(h);
					housingFundZhuMaDianRepositoryAccount.saveAll(webParam.getList());
					taskHousing.setPhase(HousingfundStatusCodeEnum.HOUSING_CRAWLER_STATEMENT_MSG_SUCCESS.getPhase());
					taskHousing.setPhase_status(HousingfundStatusCodeEnum.HOUSING_CRAWLER_STATEMENT_MSG_SUCCESS.getPhasestatus());
					taskHousing.setDescription(HousingfundStatusCodeEnum.HOUSING_CRAWLER_STATEMENT_MSG_SUCCESS.getDescription());
					taskHousing.setPaymentStatus(200);
					taskHousingRepository.save(taskHousing);
					updateTaskHousing(taskHousing.getTaskid());	
				}
				else
				{
					tracer.addTag("action.crawlerAccount.ERROR1", taskHousing.getTaskid());
					taskHousing.setPhase(HousingfundStatusCodeEnum.HOUSING_CRAWLER_STATEMENT_MSG_SUCCESS.getPhase());
					taskHousing.setPhase_status(HousingfundStatusCodeEnum.HOUSING_CRAWLER_STATEMENT_MSG_SUCCESS.getPhasestatus());
					taskHousing.setDescription(HousingfundStatusCodeEnum.HOUSING_CRAWLER_STATEMENT_MSG_SUCCESS.getDescription());
					taskHousing.setPaymentStatus(201);
					taskHousingRepository.save(taskHousing);
					updateTaskHousing(taskHousing.getTaskid());	
				}
			}
			else
			{
				//tracer.addTag("action.crawlerAccount.ERROR2", taskHousing.getTaskid());
				taskHousing.setPhase(HousingfundStatusCodeEnum.HOUSING_CRAWLER_STATEMENT_MSG_SUCCESS.getPhase());
				taskHousing.setPhase_status(HousingfundStatusCodeEnum.HOUSING_CRAWLER_STATEMENT_MSG_SUCCESS.getPhasestatus());
				taskHousing.setDescription(HousingfundStatusCodeEnum.HOUSING_CRAWLER_STATEMENT_MSG_SUCCESS.getDescription());
				taskHousing.setPaymentStatus(201);
				taskHousingRepository.save(taskHousing);
				updateTaskHousing(taskHousing.getTaskid());	
			}
		} catch (Exception e) {
			tracer.addTag("action.crawlerAccount.TIMEOUT", taskHousing.getTaskid());
			taskHousing.setPhase(HousingfundStatusCodeEnum.HOUSING_CRAWLER_STATEMENT_MSG_SUCCESS.getPhase());
			taskHousing.setPhase_status(HousingfundStatusCodeEnum.HOUSING_CRAWLER_STATEMENT_MSG_SUCCESS.getPhasestatus());
			taskHousing.setDescription(HousingfundStatusCodeEnum.HOUSING_CRAWLER_STATEMENT_MSG_SUCCESS.getDescription());
			taskHousing.setPaymentStatus(201);
			taskHousingRepository.save(taskHousing);
			updateTaskHousing(taskHousing.getTaskid());
			e.printStackTrace();
		}
		
	}

}
