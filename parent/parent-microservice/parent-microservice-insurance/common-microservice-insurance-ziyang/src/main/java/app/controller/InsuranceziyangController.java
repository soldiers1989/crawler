package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.insurance.json.InsuranceRequestParameters;
import com.microservice.dao.entity.crawler.insurance.basic.TaskInsurance;
import com.microservice.dao.repository.crawler.insurance.basic.TaskInsuranceRepository;

import app.commontracerlog.TracerLog;
import app.service.InsuranceziyangService;

@RestController
@RequestMapping("/insurance/ziyang")
public class InsuranceziyangController {
	@Autowired
	private TracerLog tracer;

	@Autowired
	private InsuranceziyangService insuranceziyangService;

	@Autowired
	private TaskInsuranceRepository taskInsuranceRepository;
	
	/**
	 * 登录 接口
	 * 
	 * @param parameter
	 * @return
	 */
	@PostMapping(value = "/login")
	public TaskInsurance login(@RequestBody InsuranceRequestParameters parameter) {
		tracer.addTag("action.login.taskid", parameter.getTaskId());
		tracer.addTag("InsuranceziyangController.login:开始登录", parameter.toString());
		// 通过taskid查出对应表中的数据
		TaskInsurance taskInsurance = taskInsuranceRepository.findByTaskid(parameter.getTaskId());
		taskInsurance.setCity(parameter.getCity());
		// 执行登录业务方法
		insuranceziyangService.login(parameter);
		return taskInsurance;
	}

	/**
	 * 爬取,解析接口
	 * 
	 * @param parameter
	 * @return
	 */
	@PostMapping(value = "/crawler")
	public TaskInsurance crawler(@RequestBody InsuranceRequestParameters parameter) {
		tracer.addTag("action.crawler.taskid", parameter.getTaskId());
		tracer.addTag("InsuranceziyangController.crawler:检测Task", parameter.toString());
		TaskInsurance taskInsurance = taskInsuranceRepository.findByTaskid(parameter.getTaskId());
		// 执行业务
		insuranceziyangService.getAllData(parameter);
		return taskInsurance;
	}

}
