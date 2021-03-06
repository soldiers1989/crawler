package org.common.microservice.eureka.china.unicom;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.springframework.util.StringUtils;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.gargoylesoftware.htmlunit.util.NameValuePair;
import com.module.htmlunit.WebCrawler;

import app.bean2.SucessResultBean;

/**   
*    
* 项目名称：common-microservice-eureka-china-unicom   
* 类名称：LianTongLoginBySelenuim   
* 类描述：   
* 创建人：hyx  
* 创建时间：2018年5月22日 上午10:08:02   
* @version        
*/
public class LianTongLoginBySelenuim {

	public static void main(String[] args) throws Exception {

		WebClient webClient = WebCrawler.getInstance().getNewWebClient();

		String url = "https://uac.10010.com/portal/mallLogin.jsp?redirectURL=http://www.10010.com/net5/";
		WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.GET);
		webRequest.setCharset(Charset.forName("UTF-8"));

		Page searchPage = webClient.getPage(webRequest);
		webClient = searchPage.getEnclosingWindow().getWebClient();
		webClient = getSmSCode(webClient);
		setphonecode(webClient);

	}

	public static WebClient getSmSCode(WebClient webClient) throws Exception {

		webClient.addRequestHeader("Host", "uac.10010.com");
		webClient.addRequestHeader("Pragma", "no-cache");

		webClient.addRequestHeader("Connection", "keep-alive");
		webClient.addRequestHeader("Referer", "https://uac.10010.com/portal/homeLogin");

		String url2 = "https://uac.10010.com/portal/Service/CheckNeedVerify?callback=jQuery172012727608617026065_"
				+ System.currentTimeMillis() + "&userName=" + "18500251272" + "&" + "pwdType=01&_="
				+ System.currentTimeMillis();
		Page page = getHtml2(url2, webClient);
		System.out.println("登录第一步url2 = " + url2 + ";" + page.getWebResponse().getContentAsString());

		String url_code = "https://uac.10010.com/portal/Service/SendCkMSG?callback=jQuery172003873314014258589_"
				+ System.currentTimeMillis() + "&req_time=" + System.currentTimeMillis() + "&mobile=" + "18500251272"
				+ "&_=" + System.currentTimeMillis();
		page = getHtml2(url_code, webClient);
		System.out.println(page.getWebResponse().getContentAsString());
		if (page.getWebResponse().getContentAsString().contains("resultCode:\"0000\"")) {

		} else {
			System.out.println("===================");
			getLoginResult(page.getWebResponse().getContentAsString());
			System.out.println("===================");
		}
		return webClient;
	}
	
	public static Object setphonecode(WebClient webClient) throws Exception {

		String valicodeStr = JOptionPane.showInputDialog("请输入验证码：");

		WebDriver driver = intiChrome();

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

		Set<Cookie> cookies = webClient.getCookieManager().getCookies();

		String url = "https://uac.10010.com/portal/homeLoginNew";

		driver.get(url);

		for (Cookie sookie : cookies) {
			driver.manage().addCookie(new org.openqa.selenium.Cookie(sookie.getName(), sookie.getValue()));
		}

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement loginname = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id("userName"));
			}
		});

		loginname.sendKeys("18500251272");

		driver.findElement(By.id("userPwd")).click();
		driver.findElement(By.id("userPwd")).sendKeys("211314");
		
		loginname.sendKeys("17600325986");

		driver.findElement(By.id("userPwd")).click();
		driver.findElement(By.id("userPwd")).sendKeys("921003");

		Thread.sleep(3000);
		WebElement userCK = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.id("userCK"));
			}
		});
		userCK.sendKeys(valicodeStr.trim());

		driver.findElement(By.id("login1")).click();
		
		wait = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		
		try{
			WebElement errormessage = wait.until(new Function<WebDriver, WebElement>() {
				
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.className("user_phone"));
				}
			});
			System.out.println("====user_phone==="+errormessage.getText());
			
			
			Set<org.openqa.selenium.Cookie> cookies_driver = driver.manage().getCookies();
//			webClient webClient2 = 
			for(org.openqa.selenium.Cookie cookie : cookies_driver){
				webClient.getCookieManager().addCookie(new Cookie(cookie.getDomain(), cookie.getName(), cookie.getValue()));
			}
			
			
			getCallThem(webClient);
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

	}

	// 通话详单查询
	public static String getCallThem(WebClient webClient) throws Exception {

		Set<Cookie> cookies = webClient.getCookieManager().getCookies();
		for (Cookie cookie : cookies) {

			System.out.println("====" + cookie.getName() + ":" + cookie.getValue());
		}

		webClient.getOptions().setJavaScriptEnabled(false);
		String url = "http://iservice.10010.com/e3/static/query/callDetail?_=1526964289307&accessURL=http://iservice.10010.com/e4/query/bill/call_dan-iframe.html&menuid=000100030001";
		List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
		paramsList = new ArrayList<NameValuePair>();
		paramsList.add(new NameValuePair("pageNo", "1"));
		paramsList.add(new NameValuePair("pageSize", "20"));
		paramsList.add(new NameValuePair("beginDate", "20180501"));
		paramsList.add(new NameValuePair("endDate", "20180521"));

		WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.POST);
		webRequest.setRequestParameters(paramsList);
		webRequest.setAdditionalHeader("Accept", "application/json, text/javascript, */*; q=0.01");
		webRequest.setAdditionalHeader("Accept-Encoding", "gzip, deflate");
		webRequest.setAdditionalHeader("Accept-Language", "zh-CN,zh;q=0.9");
		webRequest.setAdditionalHeader("Connection", "keep-alive");
		webRequest.setAdditionalHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

		webRequest.setAdditionalHeader("Host", "iservice.10010.com");
		webRequest.setAdditionalHeader("Origin", "http://iservice.10010.com");
		webRequest.setAdditionalHeader("Referer", "http://iservice.10010.com/e4/query/bill/call_dan-iframe.html");
		webRequest.setAdditionalHeader("User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
		webRequest.setAdditionalHeader("X-Requested-With", "XMLHttpRequest");
		Page searchPage = webClient.getPage(webRequest);

		System.out.println(searchPage.getWebResponse().getContentAsString());

		return searchPage.getWebResponse().getContentAsString();

	}

	/*public static Object setphonecode(WebClient webClient) throws Exception {
		webClient.addRequestHeader("Host", "uac.10010.com");
		webClient.addRequestHeader("Pragma", "no-cache");

		webClient.addRequestHeader("Connection", "keep-alive");
		webClient.addRequestHeader("Referer", "https://uac.10010.com/portal/homeLogin");

		String valicodeStr = JOptionPane.showInputDialog("请输入验证码：");

		String url = "https://uac.10010.com/portal/Service/MallLogin?" + "callback=jQuery172003873314014258589_"
				+ System.currentTimeMillis() + "&req_time=" + System.currentTimeMillis()
				+ "&redirectURL=http%3A%2F%2Fwww.10010.com" + "&userName=" + "15600018958".trim() + "&password="
				+ "100711" + "&pwdType=01&productType=01" + "&redirectType=01&rememberMe=1" + "&verifyCKCode="
				+ valicodeStr.trim() + "&_=" + System.currentTimeMillis();
		WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.GET);
		webRequest.setCharset(Charset.forName("UTF-8"));

		webRequest.setAdditionalHeader("Accept",
//				"text/javascript, application/javascript, application/ecmascript, application/x-ecmascript, *///*; q=0.01");
//		webRequest.setAdditionalHeader("Accept-Encoding", "gzip, deflate, br");
//		webRequest.setAdditionalHeader("Accept-Language", "zh-CN,zh;q=0.9");
//		webRequest.setAdditionalHeader("Connection", "keep-alive");
//		webRequest.setAdditionalHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
//
//		webRequest.setAdditionalHeader("Host", "uac.10010.com");
//		// webRequest.setAdditionalHeader("Origin",
//		// "http://iservice.10010.com");
//		webRequest.setAdditionalHeader("Referer", "https://uac.10010.com/portal/homeLoginNew");
//		webRequest.setAdditionalHeader("User-Agent",
//				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
//		webRequest.setAdditionalHeader("X-Requested-With", "XMLHttpRequest");
//
//		Page page = webClient.getPage(webRequest);
//
//		webClient = page.getEnclosingWindow().getWebClient();
//
//		System.out.println("短信验证码验证结果" + page.getWebResponse().getContentAsString());
//		System.out.println("====================短信验证码验证url=========" + url);
//
//		if (page.getWebResponse().getContentAsString().contains("resultCode:\"0000\"")) {
//
//		} else {
//			getLoginResult(page.getWebResponse().getContentAsString());
//			return null;
//		}
//		
//		Set<Cookie> cookies = webClient.getCookieManager().getCookies();
//		
//		
//		WebDriver driver = intiChrome();
//		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
//		
//		String url2 = "https://uac.10010.com/portal/mallLogin.jsp?redirectURL=http://www.10010.com/net5/";
//		driver.get(url2);
//		for(Cookie sookie : cookies){
//			driver.manage().addCookie(new org.openqa.selenium.Cookie(sookie.getName(), sookie.getValue()));
//		}	
//		
//		String baseUrl = "http://www.10010.com/net5";
//		driver.get(baseUrl);
//		Thread.sleep(10000);
//		baseUrl = "http://iservice.10010.com/e4/query/bill/call_dan-iframe.html";
//		driver.get(baseUrl);
//		Thread.sleep(10000);
//		Set<org.openqa.selenium.Cookie> cookies_driver = driver.manage().getCookies();
//		
//		for(org.openqa.selenium.Cookie cookie : cookies_driver){
//			webClient.getCookieManager().addCookie(new Cookie(cookie.getDomain(), cookie.getName(), cookie.getValue()));
//		}
//		
//		
//		 url = "http://uac.10010.com/portal/hallLogin";
//		webRequest = new WebRequest(new URL(url), HttpMethod.DELETE);
//		webRequest.setAdditionalHeader("Accept",
//				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
//		webRequest.setAdditionalHeader("Accept-Encoding", "gzip, deflate");
//		webRequest.setAdditionalHeader("Accept-Language", "zh-CN,zh;q=0.9");
//		webRequest.setAdditionalHeader("Connection", "keep-alive");
//
//		webRequest.setAdditionalHeader("Host", "uac.10010.com");
//		webRequest.setAdditionalHeader("Origin", "http://iservice.10010.com/e4/query/bill/call_dan-iframe.html");
//		webRequest.setAdditionalHeader("Referer", "http://iservice.10010.com/e4/query/bill/call_dan-iframe.html");
//		webRequest.setAdditionalHeader("User-Agent",
//				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML,like Gecko) Chrome/59.0.3071.115 Safari/537.36");
//		webRequest.setAdditionalHeader("Upgrade-Insecure-Requests", "1");
//		Page searchPage = webClient.getPage(webRequest);
//
//		System.out.println("=========halllogin========" + searchPage.getWebResponse().getContentAsString());
//		
//		System.out.println(getCallThem(webClient));
//		return null;

//	}

	// 通话详单查询
//	public static String getCallThem(WebClient webClient) throws Exception {
//
//		Set<Cookie> cookies = webClient.getCookieManager().getCookies();
//		for (Cookie cookie : cookies) {
//
//			System.out.println("====" + cookie.getName() + ":" + cookie.getValue());
//		}
//
//		webClient.getOptions().setJavaScriptEnabled(false);
//		String url = "http://iservice.10010.com/e3/static/query/callDetail?_=1526964289307&accessURL=http://iservice.10010.com/e4/query/bill/call_dan-iframe.html&menuid=000100030001";
//		List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
//		paramsList = new ArrayList<NameValuePair>();
//		paramsList.add(new NameValuePair("pageNo", "1"));
//		paramsList.add(new NameValuePair("pageSize", "20"));
//		paramsList.add(new NameValuePair("beginDate", "20180501"));
//		paramsList.add(new NameValuePair("endDate", "20180521"));
//
//		WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.POST);
//		webRequest.setRequestParameters(paramsList);
//		webRequest.setAdditionalHeader("Accept", "application/json, text/javascript, */*; q=0.01");
//		webRequest.setAdditionalHeader("Accept-Encoding", "gzip, deflate");
//		webRequest.setAdditionalHeader("Accept-Language", "zh-CN,zh;q=0.9");
//		webRequest.setAdditionalHeader("Connection", "keep-alive");
//		webRequest.setAdditionalHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
//
//		webRequest.setAdditionalHeader("Host", "iservice.10010.com");
//		webRequest.setAdditionalHeader("Origin", "http://iservice.10010.com");
//		webRequest.setAdditionalHeader("Referer", "http://iservice.10010.com/e4/query/bill/call_dan-iframe.html");
//		webRequest.setAdditionalHeader("User-Agent",
//				"Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36");
//		webRequest.setAdditionalHeader("X-Requested-With", "XMLHttpRequest");
//		Page searchPage = webClient.getPage(webRequest);
//
//		System.out.println(searchPage.getWebResponse().getContentAsString());
//
//		return searchPage.getWebResponse().getContentAsString();
//
//	}*/

	public static String getChinese(String paramValue) {
		String regex = "([\u4e00-\u9fa5]+)";
		String str = "";
		Matcher matcher = Pattern.compile(regex).matcher(paramValue);
		while (matcher.find()) {
			str += matcher.group(0);
		}
		return str;
	}

	public static void getLoginResult(String html) {
		String value = html.split("\\(\\{")[1];

		String[] values = value.split("\"\\,");

		SucessResultBean sucessResultBean = new SucessResultBean();
		for (int i = 0; i < values.length; i++) {
			if (values[i].split(":")[0].contains("resultCode")) {
				sucessResultBean.setResultCode(values[i].split(":")[1].replaceAll("\"", "").replaceAll("\\'", "") + "");
			} else if (values[i].split(":")[0].contains("msg")) {
				sucessResultBean.setMsg(getChinese(values[i].split(":")[1].replaceAll("\"", "").replaceAll("\\'", "")
						.replaceAll("。<a href=https", "")));
			}
		}

		System.out.println(sucessResultBean.toString());
	}

	public static Page getHtml2(String url, WebClient webClient) throws Exception {
		WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.GET);
		webRequest.setCharset(Charset.forName("UTF-8"));

		Page searchPage = webClient.getPage(webRequest);
		Set<Cookie> cookieSet = webClient.getCookieManager().getCookies();
		for (Cookie cookie : cookieSet) {

			System.out.println("==" + cookie.toString());
		}

		return searchPage;

	}
	
	
	static String driverPath = "C:\\chromedriver.exe";

	static Boolean headless = false;

	public static WebDriver intiChrome() throws Exception {
		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver", driverPath);

		// WebDriver driver = new ChromeDriver();
		ChromeOptions chromeOptions = new ChromeOptions();
		// 设置为 headless 模式 （必须）
		System.out.println("headless-------" + headless);
		// if(headless){
		// chromeOptions.addArguments("headless");// headless mode
		// }

		chromeOptions.addArguments("disable-gpu");
		// 设置浏览器窗口打开大小 （非必须）
		// chromeOptions.addArguments("--window-size=1920,1080");
		WebDriver driver = new ChromeDriver(chromeOptions);
		return driver;
	}
	
	

}
