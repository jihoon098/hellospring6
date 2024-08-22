package hoonspring.hellospring6;

import hoonspring.hellospring6.data.JpaOrderRepository;
import hoonspring.hellospring6.data.OrderRepository;
import hoonspring.hellospring6.data.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.jpa.JpaTransactionManager;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(){
        return new JpaOrderRepository();
    }

    @Bean
    public OrderService orderService(JpaTransactionManager transactionManager) {
        return new OrderService(orderRepository(), transactionManager);
    }
}
