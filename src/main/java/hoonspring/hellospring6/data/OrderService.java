package hoonspring.hellospring6.data;

import hoonspring.hellospring6.order.Order;
import hoonspring.hellospring6.order.OrderReq;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    Order createOrder(String no, BigDecimal total);

    List<Order> createOrders(List<OrderReq> reqs);
}
