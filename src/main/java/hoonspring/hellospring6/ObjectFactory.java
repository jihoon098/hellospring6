package hoonspring.hellospring6;

import hoonspring.hellospring6.exRate.CachedExRateProvider;
import hoonspring.hellospring6.payment.ExRateProvider;
import hoonspring.hellospring6.exRate.WebApiExRateProvider;
import hoonspring.hellospring6.payment.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
public class ObjectFactory {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new WebApiExRateProvider();
    }
}
