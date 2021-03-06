package app.client.fund;

import com.crawler.housingfund.json.TaskHousingfund;
import com.crawler.housingfund.json.MessageLoginForHousing;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("HousingFund-BeiJing")
public interface FundBeijingClient {

    @PostMapping(value="/housing/beijing/crawler")
    public TaskHousingfund crawler(@RequestBody MessageLoginForHousing messageLoginForHousing);
}
