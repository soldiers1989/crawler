package app.client.fund;

import com.crawler.housingfund.json.TaskHousingfund;
import com.crawler.housingfund.json.MessageLoginForHousing;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("HousingFund-DaQing")
public interface FundDaqingClient {

    @PostMapping(value="/housing/daqing/login")
    public TaskHousingfund login(@RequestBody MessageLoginForHousing messageLoginForHousing);

    @PostMapping(value="/housing/daqing/crawler")
    public TaskHousingfund crawler(@RequestBody MessageLoginForHousing messageLoginForHousing);
}
