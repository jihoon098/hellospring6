package hoonspring.hellospring6;

import hoonspring.hellospring6.data.OrderRepository;
import hoonspring.hellospring6.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionExecution;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        OrderRepository repository = beanFactory.getBean(OrderRepository.class);
        JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

        try {
            // 스프링이 적절한 타이밍에 자동으로 트랜잭션 begin, commit 제어하는 Template
            new TransactionTemplate(transactionManager).execute((TransactionCallback<Order>) status -> {

                Order order = new Order("100", BigDecimal.TEN);
                repository.save(order);

                Order order2 = new Order("100", BigDecimal.ONE);
                repository.save(order2);

                return null;
            });
        }
        // 특정한 DB 기술에 종속적이지 않은 일관된 예외를 스프링이 던져줌.
        catch (DataIntegrityViolationException e) {
            System.out.println("주민번호 중복 복구 작업");
        }
    }
}
