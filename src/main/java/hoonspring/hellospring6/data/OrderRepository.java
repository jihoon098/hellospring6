package hoonspring.hellospring6.data;

import hoonspring.hellospring6.order.Order;

public interface OrderRepository {
    public void save(Order order);
}
