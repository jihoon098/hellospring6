package hoonspring.hellospring6;

import hoonspring.hellospring6.data.OrderRepository;
import hoonspring.hellospring6.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);

        Order order = new Order("100", BigDecimal.TEN);
        repository.save(order);

        /*
         * org.hibernate.exception의 ConstraintViolationException 발생.
         * 만약 예외를 복구하려면, ConstraintViolationException을 catch 해야함.
         */
        Order order2 = new Order("100", BigDecimal.ONE);
        repository.save(order2);
    }
}
