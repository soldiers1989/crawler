package app.service.aop;

import com.crawler.bank.json.BankJsonBean;
import com.microservice.dao.entity.crawler.bank.basic.TaskBank;

/**
 * 爬取接口，适用于登录就可以直接爬取的情况（例如地区的电信运营商可以跳过短信验证码的情况），可直接实现此接口
 * 
 * 
 * */
public interface ICrawlerLogin extends ICrawler{
	
	/**
	 * 登录接口
	 * 
	 * */
	public TaskBank login(BankJsonBean bankJsonBean);

}
