package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.insurance.json.InsuranceRequestParameters;
import com.microservice.dao.entity.crawler.insurance.basic.TaskInsurance;
import com.microservice.dao.repository.crawler.insurance.basic.TaskInsuranceRepository;

import app.commontracerlog.TracerLog;
import app.service.InsuranceHuangShiService;
import app.service.InsuranceService;

@RestController
@Configuration
@RequestMapping("/insurance")
public class InsuranceHuangShiController {

	@Autowired
	private TaskInsuranceRepository taskInsuranceRepository;
	@Autowired
	private InsuranceHuangShiService insuranceHuangShiService;
	@Autowired
	private InsuranceService insuranceService;
	@Autowired
	private TracerLog tracer; 
	
	
	@PostMapping(value="/huangshi")
	public TaskInsurance login(@RequestBody InsuranceRequestParameters insuranceRequestParameters){
		tracer.addTag("parser.login",insuranceRequestParameters.getTaskId());
		TaskInsurance taskInsurance = taskInsuranceRepository.findByTaskid(insuranceRequestParameters.getTaskId());
		insuranceService.changeLoginStatusDoing(taskInsurance);
		tracer.addTag("parser.login.taskInsurance", taskInsurance.toString());
		try {
			taskInsurance = insuranceHuangShiService.login(insuranceRequestParameters);
		} catch (Exception e) {
			e.printStackTrace();
			tracer.addTag("crawler.login.controller.Exception", e.toString());
			insuranceService.changeLoginStatusException(taskInsurance);
		}
		taskInsurance = taskInsuranceRepository.findByTaskid(insuranceRequestParameters.getTaskId());
		return taskInsurance;
	}
	
	@PostMapping(value="/huangshi/getData")
	public TaskInsurance crawler(@RequestBody InsuranceRequestParameters insuranceRequestParameters) throws Exception{
		tracer.qryKeyValue("taskid", insuranceRequestParameters.getTaskId());
		tracer.addTag("parser.crawler"," ----crawler----");
		TaskInsurance taskInsurance = insuranceService.changeCrawlerStatusDoing(insuranceRequestParameters);
		taskInsurance = insuranceHuangShiService.getAllData(insuranceRequestParameters);
		
		return taskInsurance;
	}
}
