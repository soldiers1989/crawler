package app.client.fund;

import com.crawler.housingfund.json.MessageLoginForHousing;
import com.crawler.housingfund.json.TaskHousingfund;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("HousingFund-XingTai")
public interface FundXingtaiClient {

    @PostMapping(value="/housing/xingtai/login")
    public TaskHousingfund login(@RequestBody MessageLoginForHousing messageLoginForHousing);

    @PostMapping(value="/housing/xingtai/crawler")
    public TaskHousingfund crawler(@RequestBody MessageLoginForHousing messageLoginForHousing);
}
