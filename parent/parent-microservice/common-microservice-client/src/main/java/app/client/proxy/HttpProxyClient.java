package app.client.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crawler.aws.json.HttpProxyRes;

import app.client.MiddleClientConfig;

@FeignClient(name = "api-ip",url="https://apitxboss.txtechnologies.com")
public interface HttpProxyClient {

	//获取代理IP、端口
//	@GetMapping(value = "/aws/api/proxy/get")
	@RequestMapping(value = "/api-service/proxy/get",method = RequestMethod.GET)
	public HttpProxyRes getProxy(@RequestParam(name = "num",required=false) String num, @RequestParam(name = "pro",required=false) String pro);

}

