package hoonspring.hellospring6.exRate;

import hoonspring.hellospring6.payment.ExRateProvider;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class RestTemplateExRateProvider implements ExRateProvider {

    private final RestTemplate restTemplate;

    public RestTemplateExRateProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        // get메소드를 사용해서 가져오는 건데 그래서 여기서 getForObject라는 걸
        return restTemplate.getForObject(url, ExRateData.class).rates().get("KRW");
    }
}
