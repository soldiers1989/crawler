package app.client.carrier;

import com.crawler.mobile.json.MessageLogin;
import com.crawler.mobile.json.ResultData;
import com.crawler.mobile.json.TaskMobile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("crawler-telecom-liaoning")
public interface TelecomLiaoNingClient {

    @PostMapping("/carrier/liaoning/crawler")
    public ResultData<TaskMobile> telecom(@RequestBody MessageLogin messageLogin);
}
