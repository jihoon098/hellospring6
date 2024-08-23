package hoonspring.hellospring6;

import hoonspring.hellospring6.data.JdbcOrderRepository;
import hoonspring.hellospring6.data.OrderRepository;
import hoonspring.hellospring6.data.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(DataSource dataSource){
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(
            OrderRepository orderRepository, PlatformTransactionManager transactionManager
    ) {
        return new OrderService(orderRepository, transactionManager);
    }
}
