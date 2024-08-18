package hoonspring.hellospring6;

import hoonspring.hellospring6.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class DataClient {
    public static void main(String[] args) {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);
        
        // 1. EntityManagerFactory를 이용해 EntityManager 생성
        EntityManager em = emf.createEntityManager();

        // 2. Transaction 생성. 모든 DB작업은 Transaction안에서 일어나야함.
        em.getTransaction().begin();

        Order order = new Order("100", BigDecimal.TEN);
        em.persist(order);

        System.out.println(order);

        em.getTransaction().commit();
        em.close();
    }
}
