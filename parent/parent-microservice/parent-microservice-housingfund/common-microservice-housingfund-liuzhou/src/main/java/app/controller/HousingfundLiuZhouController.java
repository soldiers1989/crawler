package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.housingfund.json.MessageLoginForHousing;
import com.crawler.mobile.json.ResultData;
import com.crawler.mobile.json.StatusCodeEnum;
import com.crawler.mobile.json.StatusCodeLogin;
import com.google.gson.Gson;
import com.microservice.dao.entity.crawler.housing.basic.TaskHousing;

import app.commontracerlog.TracerLog;
import app.service.HousingfundLiuZhouService;
import app.service.common.aop.ICrawlerLogin;


@RestController
@Configuration
@RequestMapping("/housing/liuzhou") 
public class HousingfundLiuZhouController extends HousingBasicController{

	@Autowired
	private TracerLog tracer;
	@Autowired
	private HousingfundLiuZhouService housingfundLiuZhouService;
	@Autowired
	private ICrawlerLogin iCrawlerLogin;
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public TaskHousing login(@RequestBody MessageLoginForHousing messageLoginForHousing) {
		tracer.addTag("action.cangzhou.login", messageLoginForHousing.getTask_id());
		TaskHousing taskHousing = iCrawlerLogin.login(messageLoginForHousing);
		return taskHousing;
	}
	
	@RequestMapping(value = "/crawler", method = RequestMethod.POST )
	public TaskHousing crawler(@RequestBody MessageLoginForHousing messageLogin){
		tracer.addTag("parser.crawler.taskid", messageLogin.getTask_id());
		tracer.addTag("parser.crawler.auth", messageLogin.getNum());
		TaskHousing taskHousing = iCrawlerLogin.getAllData(messageLogin);
		return taskHousing;
	}
}
