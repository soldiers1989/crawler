package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.housingfund.json.MessageLoginForHousing;
import com.crawler.mobile.json.ResultData;
import com.microservice.dao.entity.crawler.housing.basic.TaskHousing;

import app.commontracerlog.TracerLog;
import app.service.HousingFundHaErBinService;

@RestController
@Configuration
@RequestMapping("/housing/haerbin")
public class HousingFundHaErBinController extends HousingBasicController{

	@Autowired
	private HousingFundHaErBinService housingFundHaErBinService;
	@Autowired
	private TracerLog tracer;
	
	    //登陆
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public ResultData<TaskHousing> housingFundLogin(@RequestBody MessageLoginForHousing messageLoginForHousing) throws Exception {
			TaskHousing taskHousing = findTaskHousing(messageLoginForHousing.getTask_id());
			tracer.addTag("parser.housingFund.login.start",messageLoginForHousing.getTask_id());
			ResultData<TaskHousing> result = new ResultData<TaskHousing>();

//			taskHousing.setPhase(StatusCodeEnum.TASKMOBILE_LOGIN_LOADING.getPhase());
//			taskHousing.setPhase_status(StatusCodeEnum.TASKMOBILE_LOGIN_LOADING.getPhasestatus());
//			taskHousing.setDescription(StatusCodeEnum.TASKMOBILE_LOGIN_LOADING.getDescription());
//			taskHousing.setError_code(StatusCodeEnum.TASKMOBILE_LOGIN_LOADING.getError_code());
//			save(taskHousing);
			
			taskHousing =housingFundHaErBinService.login(messageLoginForHousing);
			
			result.setData(taskHousing);
			return result;
		}
		
		
		//爬取
		@RequestMapping(value = "/crawler", method = RequestMethod.POST)
		public ResultData<TaskHousing> housingFundCrawler(@RequestBody MessageLoginForHousing messageLoginForHousing) {
			TaskHousing taskHousing = findTaskHousing(messageLoginForHousing.getTask_id());
			tracer.addTag("parser.housingFund.crawler.start",messageLoginForHousing.getTask_id());
			ResultData<TaskHousing> result = new ResultData<TaskHousing>();

//			taskHousing.setPhase(StatusCodeEnum.TASKMOBILE_CRAWLER_READ.getPhase());
//			taskHousing.setPhase_status(StatusCodeEnum.TASKMOBILE_CRAWLER_READ.getPhasestatus());
//			taskHousing.setDescription(StatusCodeEnum.TASKMOBILE_CRAWLER_READ.getDescription());
//			taskHousing.setError_code(StatusCodeEnum.TASKMOBILE_CRAWLER_READ.getError_code());
//			save(taskHousing);
			
			taskHousing =housingFundHaErBinService.getAllData(messageLoginForHousing);
			
			result.setData(taskHousing);
			return result;
			
			
		}
		
		
		//验证码
		@RequestMapping(value = "/getCode", method = RequestMethod.POST)
		public ResultData<TaskHousing> housingFundGet(@RequestBody MessageLoginForHousing messageLoginForHousing) {
			TaskHousing taskHousing = findTaskHousing(messageLoginForHousing.getTask_id());
			tracer.addTag("parser.housingFund.getCode.start",messageLoginForHousing.getTask_id());
			ResultData<TaskHousing> result = new ResultData<TaskHousing>();

//			taskHousing.setPhase(StatusCodeEnum.TASKMOBILE_READY_CODE_DONING.getPhase());
//			taskHousing.setPhase_status(StatusCodeEnum.TASKMOBILE_READY_CODE_DONING.getPhasestatus());
//			taskHousing.setDescription(StatusCodeEnum.TASKMOBILE_READY_CODE_DONING.getDescription());
//			taskHousing.setError_code(StatusCodeEnum.TASKMOBILE_READY_CODE_DONING.getError_code());
//			save(taskHousing);
			
			taskHousing =housingFundHaErBinService.sendSms(messageLoginForHousing);
			result.setData(taskHousing);
			return result;
			
			
		}
}
