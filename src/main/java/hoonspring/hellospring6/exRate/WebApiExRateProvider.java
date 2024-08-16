package hoonspring.hellospring6.exRate;

import hoonspring.hellospring6.api.ApiTemplate;
import hoonspring.hellospring6.api.ErApiExRateExtractor;
import hoonspring.hellospring6.api.HttpClientApiExecutor;
import hoonspring.hellospring6.api.SimpleApiExecutor;
import hoonspring.hellospring6.payment.ExRateProvider;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class WebApiExRateProvider implements ExRateProvider {

    private final ApiTemplate apiTemplate;

    public WebApiExRateProvider(ApiTemplate apiTemplate) {
        this.apiTemplate = apiTemplate;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return apiTemplate.getExRate(url);
    }
}
