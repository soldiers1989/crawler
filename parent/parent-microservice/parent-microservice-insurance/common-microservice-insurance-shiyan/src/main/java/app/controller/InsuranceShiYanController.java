package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.insurance.json.InsuranceRequestParameters;
import com.microservice.dao.entity.crawler.insurance.basic.TaskInsurance;

import app.commontracerlog.TracerLog;
import app.service.InsuranceService;
import app.service.InsuranceShiYanService;


@RestController
@Configuration
@RequestMapping("/insurance/shiyan") 
public class InsuranceShiYanController {

	@Autowired
	private InsuranceShiYanService insuranceZhongshanService;

	@Autowired
	private InsuranceService insuranceService;
	
	@Autowired 
	private TracerLog	 tracer;
	 
	
	
	/**
	 * 获取数据
	 * @param insuranceRequestParameters
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value="/crawler")
	public TaskInsurance crawler(@RequestBody InsuranceRequestParameters insuranceRequestParameters) throws Exception{
		
		tracer.addTag("InsuranceShiyanController.crawler", insuranceRequestParameters.getTaskId());
		
		tracer.addTag("parser.crawler.taskid",insuranceRequestParameters.getTaskId());
		
		TaskInsurance taskInsurance = null;
		
		boolean isCrawler = insuranceService.isDoing(insuranceRequestParameters);
		
		if(isCrawler){
			tracer.addTag("正在进行上次未完成的爬取任务。。。",insuranceRequestParameters.toString());
		}else{
			taskInsurance = insuranceZhongshanService.updateTaskInsurance(insuranceRequestParameters);	
			insuranceZhongshanService.crawler(insuranceRequestParameters);
		}
		
	
		return taskInsurance;
		
	}
	
}
