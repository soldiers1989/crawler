
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import javax.swing.JOptionPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.gargoylesoftware.htmlunit.HttpMethod;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebRequest;
import com.gargoylesoftware.htmlunit.util.Cookie;
import com.module.htmlunit.WebCrawler;

/**
 * 
 * 项目名称：common-microservice-housingfund-yvlin 类名称：yvlintest 类描述： 创建人：hyx
 * 创建时间：2018年1月4日 上午10:44:24
 * 
 * @version
 */
public class suqiantest {

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

	public static WebClient loginChrome() throws Exception {
		WebDriver driver = intiChrome();
		// WebDriver driver = new RemoteWebDriver(new
		// URL("http://10.167.202.218:32768//wd/hub/"),
		// DesiredCapabilities.chrome());
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		String baseUrl = "http://www.sqsbjf2.cn:9888/uaa/personlogin#/personLogin";
		driver.get(baseUrl);
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(By.name("username"));
			}
		});
		driver.findElement(By.name("username")).sendKeys("321321199410084871");

		driver.findElement(By.name("password")).sendKeys("nk123456");

		String code = JOptionPane.showInputDialog("请输入验证码：");

		driver.findElement(By.name("captchaWord")).sendKeys(code);

		driver.findElement(By.xpath("//*[@id='loginForm']/div[8]/button")).click();

		Thread.sleep(5000);

		Set<org.openqa.selenium.Cookie> cookiesDriver = driver.manage().getCookies();
		WebClient webClient = WebCrawler.getInstance().getNewWebClient();

		Map<String, String> cookiemap = new HashMap<>();
		for (org.openqa.selenium.Cookie cookie : cookiesDriver) {
			Cookie cookieWebClient = new Cookie(cookie.getDomain(), cookie.getName(), cookie.getValue());
			cookiemap.put(cookie.getName(), cookie.getValue());
			System.out.println(
					cookieWebClient.getDomain() + ":" + cookieWebClient.getName() + ":" + cookieWebClient.getValue());
			webClient.getCookieManager().addCookie(cookieWebClient);
		}
		try {
			wait = new FluentWait<WebDriver>(driver).withTimeout(20, TimeUnit.SECONDS).pollingEvery(2, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class);
			wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.className("modal-header"));
				}
			});
		} catch (Exception e) {

			Document doc = Jsoup.parse(driver.getPageSource());
			System.out.println("=======================================================");
			System.out.println(doc.select("div.alert-danger").text());
			System.out.println("=======================================================");
		}

		// driver.close();
		// System.out.println("===="+htmlsource3);
		return webClient;
		//
	}

	public static Page getUser(WebClient webClient) throws Exception {
		String url = "http://www.sqsbjf2.cn:9888/api/security/user";
		Page page = getHtml(url, webClient); //

		System.out.println(page.getWebResponse().getContentAsString());
		return page;
	}

	public static Page getPay(WebClient webClient, String personId) throws Exception {
		String url = "http://www.sqsbjf2.cn:9888/ehrss-si-person/api/rights/paymenthome/detail/query?"
				+ "endDate=201802&" + "personId=" + personId.trim() + "&startDate=200002";

		Page page = getHtml(url, webClient); //

		System.out.println(page.getWebResponse().getContentAsString());

		return page;
	}

	public static Page getHtml(String url, WebClient webClient) throws Exception {
		WebRequest webRequest = new WebRequest(new URL(url), HttpMethod.GET);
		webClient.setJavaScriptTimeout(50000);
		webClient.getOptions().setTimeout(50000); // 15->60
		Page searchPage = webClient.getPage(webRequest);
		return searchPage;
	}

	public static void main(String[] args) {
		try {

			// LocalDate nowdate = LocalDate.now();
			//
			// String beginDate = nowdate.plusYears(-10).getYear() + "01";
			// String endDate = nowdate.getYear() + "01";
			// String month = nowdate.getMonthValue()+"";
			// if(month.length()==1){
			// month = "0"+month;
			// }
			//
			// System.out.println(nowdate.getYear()+"====="+month);

			 WebClient webClient = loginChrome();

			
			 Page searchPage = getUser(webClient);
			  System.out.println(searchPage.getWebResponse().getContentAsString());
////			  InsuranceBasicSuQianBean result =
////			  InsuranceSuQianParse.basicusereParse(searchPage.getWebResponse().
////			  getContentAsString());
////			  
////			 List<AssociatedPersons> list = result.getAssociatedPersons();
////			  
////			  for(AssociatedPersons associatedPersons : list){ String personId
////			  = associatedPersons.getId()+""; Page page = getPay(webClient,
////			  personId);
////			  
////			  InsuranceSuQianParse.PayParse(page.getWebResponse().
//			  getContentAsString()); }
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
