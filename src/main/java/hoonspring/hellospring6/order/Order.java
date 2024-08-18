package hoonspring.hellospring6.order;

import jakarta.persistence.*;

import java.math.BigDecimal;

/*
 * @Entity: 데이터베이스 테이블과 매핑되는 자바 클래스임을 명시
 * @Table: 매핑될 데이터베이스 테이블의 이름을 지정
 * @Id: Entity 클래스의 기본 키(식별자) 필드를 지정
 * @GeneratedValue: 기본 키 값을 DB에서 자동으로 생성하는 전략을 지정
 * @Column(unique = true): 해당 컬럼의 값이 데이터베이스에서 고유해야 함을 명시
 */
@Entity
@Table(name = "orders")
public class Order {
    
    @Id @GeneratedValue
    private Long id; // DB에 넣을 고유번호로 사용할 주문 ID

    @Column(unique = true)
    private String no; // 서비스를 이용하는 유저들한테 보여 주기 위한 의미 있는 주문번호

    private BigDecimal total; //  지불해야 되는 전체 금액

    /*
     * JPA(Jakarta Persistence API)를 사용할 때 주의할 점
     * : 파라미터가 있는 생성자를 만들었을 때, 기본 생성자도 꼭 생성해줘야 함.
     */
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
