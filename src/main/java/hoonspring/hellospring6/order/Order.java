package hoonspring.hellospring6.order;

import java.math.BigDecimal;

public class Order {

    private Long id; // DB에 넣을 고유번호로 사용할 주문 ID
    private String no; // 서비스를 이용하는 유저들한테 보여 주기 위한 의미 있는 주문번호
    private BigDecimal total; //  지불해야 되는 전체 금액

    public Order() {
    }

    public Order(String no, BigDecimal total) {
        this.no = no;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public String getNo() {
        return no;
    }

    public BigDecimal getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", no='" + no + '\'' +
                ", total=" + total +
                '}';
    }
}
