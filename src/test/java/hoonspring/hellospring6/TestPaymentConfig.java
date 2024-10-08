package hoonspring.hellospring6;

import hoonspring.hellospring6.payment.ExRateProvider;
import hoonspring.hellospring6.payment.ExRateProviderStub;
import hoonspring.hellospring6.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

/*
 * @Configuration
 * : 기존 평범한 클래스인 ObjectFactory가 '구성 정보'를 가지고 있는 클래스다라는 것을 스프링에게 알려주는 어노테이션.
 *   말그대로 Config정보를 가지고있는 클래스를 의미.
 *
 * @ComponentScan
 * : @Component가 붙은 클래스를 찾아서 오브젝트를 생성하고 의존을 주입하는 역할을 스프링이 내부적으로 하게 만듬.
 *   스프링이 필요로 하는 것은 '빈과 관련된 정보'
 */
@Configuration
public class TestPaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider(), clock());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new ExRateProviderStub(BigDecimal.valueOf(1_000));
    }

    @Bean
    public Clock clock() {
        return Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }
}
