package hoonspring.hellospring6.data;

import hoonspring.hellospring6.order.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.math.BigDecimal;

public class OrderRepository {

    private EntityManagerFactory emf;

    public OrderRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Order order) {
        // 1. EntityManagerFactory를 이용해 EntityManager 생성
        // EntityManager는 매 DB작업마다(트랜잭션마다) 새로운 오브젝트로 만들어져함.
        EntityManager em = this.emf.createEntityManager();

        // 2. Transaction 생성. 모든 DB작업은 Transaction안에서 일어나야함.
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {
            em.persist(order);

            transaction.commit();
        }
        catch (RuntimeException e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            if(em.isOpen()) em.close();
        }
    }
}
