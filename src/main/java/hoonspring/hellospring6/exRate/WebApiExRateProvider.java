package hoonspring.hellospring6.exRate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hoonspring.hellospring6.api.ApiExecutor;
import hoonspring.hellospring6.api.ErApiExRateExtractor;
import hoonspring.hellospring6.api.ExRateExtractor;
import hoonspring.hellospring6.api.SimpleApiExecutor;
import hoonspring.hellospring6.payment.ExRateProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class WebApiExRateProvider implements ExRateProvider {

    @Override
    public BigDecimal getExRate(String currency) {
        String url = "https://open.er-api.com/v6/latest/" + currency;

        return runApiforExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
    }

    // 메소드 추출(Api를 호출하여 환율정보를 가져오는 기본적인 틀 분리)
    private static BigDecimal runApiforExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch(IOException e) {
            throw new RuntimeException();
        }

        try {
            return exRateExtractor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
