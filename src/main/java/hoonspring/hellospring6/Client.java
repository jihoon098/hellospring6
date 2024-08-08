package hoonspring.hellospring6;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

    public static void main(String[] args) throws IOException {
        /*
         * 스프링인 BeanFactory는 어플리케이션 구성정보를 모름.
         * 어플리케이션의 구성정보를 스프링컨테이너가 사용하게 해줘야함.
         */
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(ObjectFactory.class);
        PaymentService paymentService = (PaymentService) beanFactory.getBean(PaymentService.class);

        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(payment);
    }
}
