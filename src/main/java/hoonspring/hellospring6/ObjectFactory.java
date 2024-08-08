package hoonspring.hellospring6;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * @Configuration
 * : 기존 평범한 클래스인 ObjectFactory가 '구성 정보'를 가지고 있는 클래스다라는 것을 스프링에게 알려주는 어노테이션.
 *   말그대로 Config정보를 가지고있는 클래스를 의미.
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
