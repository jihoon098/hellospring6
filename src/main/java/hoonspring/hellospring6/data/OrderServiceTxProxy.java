package hoonspring.hellospring6.data;

import hoonspring.hellospring6.order.Order;
import hoonspring.hellospring6.order.OrderReq;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceTxProxy implements OrderService{

    private final OrderService target;
    private final PlatformTransactionManager transactionManager;

    public OrderServiceTxProxy(OrderService orderService, PlatformTransactionManager transactionManager) {
        this.target = orderService;
        this.transactionManager = transactionManager;
    }

    @Override
    public Order createOrder(String no, BigDecimal total) {
        return new TransactionTemplate(transactionManager).execute(status ->
            this.target.createOrder(no, total)
        );
    }

    @Override
    public List<Order> createOrders(List<OrderReq> reqs) {
        return new TransactionTemplate(transactionManager).execute(status ->
            this.target.createOrders(reqs)
        );
    }
}
