package app.contoller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.housingfund.json.MessageLoginForHousing;
import com.crawler.mobile.json.ResultData;
import com.microservice.dao.entity.crawler.housing.basic.TaskHousing;

import app.controller.HousingBasicController;
import app.service.HousingZhaoQingFutureService;

@RestController
@Configuration
@RequestMapping("/housing/zhaoqing")
public class HousingFundZhaoQingController extends HousingBasicController{
	public static final Logger log = LoggerFactory.getLogger(HousingFundZhaoQingController.class);
	@Autowired
	public HousingZhaoQingFutureService housingZhaoQingFutureService;
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public MessageLoginForHousing login(@RequestBody MessageLoginForHousing messageLoginForHousing) {
		tracer.addTag("action.cangzhou.login", messageLoginForHousing.getTask_id());
		TaskHousing taskHousing = findTaskHousing(messageLoginForHousing.getTask_id());
		housingZhaoQingFutureService.login(messageLoginForHousing);
		return new MessageLoginForHousing();
	}
	
	@RequestMapping(value = "/crawler", method = RequestMethod.POST)
	public ResultData<TaskHousing> crawler(@RequestBody MessageLoginForHousing messageLoginForHousing) {
		TaskHousing taskHousing = findTaskHousing(messageLoginForHousing.getTask_id());

		ResultData<TaskHousing> result = new ResultData<TaskHousing>();
		
		taskHousing = housingZhaoQingFutureService.getAllData(messageLoginForHousing);
		
		result.setData(taskHousing);
		return result;
		
		
	}

}
