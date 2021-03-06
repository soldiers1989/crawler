package app.client.insur;

import com.crawler.insurance.json.TaskInsurance;
import com.crawler.insurance.json.InsuranceRequestParameters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name ="Insurance-Nanchang",configuration = InsurClientConfig.class)
public interface InsurNanchangClient {

    @PostMapping(value="/insurance/nanchang/login")
    public TaskInsurance login(@RequestBody InsuranceRequestParameters insuranceRequestParameters);

    @PostMapping(value="/insurance/nanchang/getAllData")
    public TaskInsurance crawler(@RequestBody InsuranceRequestParameters insuranceRequestParameters);



}
