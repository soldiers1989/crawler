package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crawler.bank.json.BankJsonBean;
import com.crawler.bank.json.BankStatusCode;
import com.microservice.dao.entity.crawler.bank.basic.TaskBank;

import app.commontracerlog.TracerLog;
import app.service.AgentService;
import app.service.ServiceBOHCChinaAop;
import app.service.TaskBankStatusService;

@RestController
@Configuration
@RequestMapping("/bank/bohcchina")
public class ControllerBOHCChinaAop {
	@Autowired
	private TracerLog tracerLog;
	@Autowired
	private TaskBankStatusService taskBankStatusService;
	@Autowired
	private ServiceBOHCChinaAop serviceBOHCChinaAop;

	@Autowired
	private AgentService agentService;
	@Value("${spring.application.name}")
	String appName;

	// 登录中间层
	@PostMapping(path = "/loginAgent")
	public TaskBank loginAgent(@RequestBody BankJsonBean bankJsonBean){
		tracerLog.qryKeyValue("渤海银行（储蓄卡）登录集群的调用...", bankJsonBean.getTaskid());
		TaskBank taskBank = null;
		try {
			taskBank = agentService.postAgent(bankJsonBean, "/bank/bohcchina/login", 180000L);
		} catch (RuntimeException e) {
			tracerLog.qryKeyValue("RuntimeException", e.toString());
		}

		return taskBank;
	}

	// 登录业务
	@PostMapping(path = "/login")
	public TaskBank login(@RequestBody BankJsonBean bankJsonBean) {

		tracerLog.qryKeyValue("渤海银行（储蓄卡）登录的业务调用...", bankJsonBean.getTaskid());
		try {
			serviceBOHCChinaAop.login(bankJsonBean);
		} catch (Exception e) {
			tracerLog.addTag("渤海银行（储蓄卡）登录的业务调用异常...", e.getMessage());
		}

		return new TaskBank();
	}

	//发送验证码中间层
	@PostMapping(path = "/sendSmsAgent")
	public TaskBank sendSmsAgent(@RequestBody BankJsonBean bankJsonBean){ 
		tracerLog.qryKeyValue("渤海银行（储蓄卡）发送验证码集群的调用...", bankJsonBean.getTaskid());
		TaskBank taskBank;
		try {
			taskBank = agentService.postAgent(bankJsonBean, "/bank/bohcchina/sendSms");
		} catch (RuntimeException e) {
			taskBank = taskBankStatusService.changeStatus(BankStatusCode.BANK_AGENT_ERROR.getPhase(),
					BankStatusCode.BANK_AGENT_ERROR.getPhasestatus(), BankStatusCode.BANK_AGENT_ERROR.getDescription(),
					BankStatusCode.BANK_AGENT_ERROR.getError_code(), true, bankJsonBean.getTaskid());
		}

		return taskBank;
	}
	
	//发送验证码
	@PostMapping(path = "/sendSms")
	public TaskBank sendSms(@RequestBody BankJsonBean bankJsonBean){
		
		tracerLog.qryKeyValue("渤海银行（储蓄卡）发送验证码的业务调用...", bankJsonBean.getTaskid());
		TaskBank sendSms = null;
		try {
			 sendSms = serviceBOHCChinaAop.sendSms(bankJsonBean);
		} catch (Exception e) {
			e.printStackTrace();
			tracerLog.addTag("渤海银行（储蓄卡）发送验证码的业务调用异常...", e.getMessage());
		}
		return sendSms;
	}
	//验证验证码中间层
	@PostMapping(path = "/verifySmsAgent")
	public TaskBank verifySmsAgent(@RequestBody BankJsonBean bankJsonBean){ 
		tracerLog.qryKeyValue("渤海银行（储蓄卡）验证验证码集群的调用...", bankJsonBean.getTaskid());
		TaskBank taskBank;
		try {
			taskBank = agentService.postAgent(bankJsonBean, "/bank/bohcchina/verifySms");
		} catch (RuntimeException e) {
			taskBank = taskBankStatusService.changeStatus(BankStatusCode.BANK_AGENT_ERROR.getPhase(),
					BankStatusCode.BANK_AGENT_ERROR.getPhasestatus(), BankStatusCode.BANK_AGENT_ERROR.getDescription(),
					BankStatusCode.BANK_AGENT_ERROR.getError_code(), true, bankJsonBean.getTaskid());
		}
		return taskBank;
	}
	
	//验证验证码
	@PostMapping(path = "/verifySms")
	public TaskBank verifySms(@RequestBody BankJsonBean bankJsonBean){
		
		tracerLog.qryKeyValue("渤海银行（储蓄卡）验证验证码的业务调用...", bankJsonBean.getTaskid());
		
		try {
			serviceBOHCChinaAop.verifySms(bankJsonBean);
		} catch (Exception e) {
			tracerLog.addTag("渤海银行（储蓄卡）验证验证码的业务调用异常...", e.getMessage());
		}
		return new TaskBank();
	}
	
	
	
	//爬取和解析中间层
	@PostMapping(path = "/getAllDataAgent")
	public TaskBank getAllDataAgent(@RequestBody BankJsonBean bankJsonBean){ 
		tracerLog.qryKeyValue("渤海银行（储蓄卡）爬取和解析集群的调用...", bankJsonBean.getTaskid());
		TaskBank taskBank;
		try {
			taskBank = agentService.postAgent(bankJsonBean, "/bank/bohcchina/getAllData");
		} catch (RuntimeException e) {
			taskBank = taskBankStatusService.changeStatus(BankStatusCode.BANK_AGENT_ERROR.getPhase(),
					BankStatusCode.BANK_AGENT_ERROR.getPhasestatus(), BankStatusCode.BANK_AGENT_ERROR.getDescription(),
					BankStatusCode.BANK_AGENT_ERROR.getError_code(), true, bankJsonBean.getTaskid());
		}
		return taskBank;
	}
	//爬取和解析
	@PostMapping(path = "/getAllData")
	public TaskBank getAllData(@RequestBody BankJsonBean bankJsonBean){
		tracerLog.qryKeyValue("渤海银行（储蓄卡）爬取和解析的业务调用...", bankJsonBean.getTaskid());
		try {
			serviceBOHCChinaAop.getAllData(bankJsonBean);
		} catch (Exception e) {
			tracerLog.addTag("渤海银行（储蓄卡）爬取和解析的业务调用异常...", e.getMessage());
		}
		return new TaskBank();
	}
	
	@PostMapping(path = "/quit")
	public TaskBank quit(@RequestBody BankJsonBean bankJsonBean) {
		TaskBank taskBank = serviceBOHCChinaAop.quit(bankJsonBean);
		return taskBank;
	}
}
