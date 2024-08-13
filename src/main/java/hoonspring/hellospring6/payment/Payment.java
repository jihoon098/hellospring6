package hoonspring.hellospring6.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

public class Payment {

    private Long orderId; // 주문번호
    private String currency; // 통화의 종류(USD, KRW, JPY 등)
    private BigDecimal foreignCurrencyAmount; // 통화 종류에 따른 결제 금액
    private BigDecimal exchangeRate; // 환율
    private BigDecimal convertedAmount; // 원화 금액
    private LocalDateTime validUntil; // 환율에 따른 결제 금액의 적용 유효시간

    public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exchangeRate, BigDecimal convertedAmount, LocalDateTime validUntil) {
        this.orderId = orderId;
        this.currency = currency;
        this.foreignCurrencyAmount = foreignCurrencyAmount;
        this.exchangeRate = exchangeRate;
        this.convertedAmount = convertedAmount;
        this.validUntil = validUntil;
    }

    static Payment createPrepared(Long orderId, String currency, BigDecimal foreignCurrencyAmount, BigDecimal exchangeRate,
                                  LocalDateTime now) {
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exchangeRate);
        LocalDateTime validUntil = now.plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exchangeRate, convertedAmount, validUntil);
    }

    // 유효시간을 검증하는 코드를 vo안에 둬서 사용하는 쪽에서 유효시간 체크 로직을 별도로 작성할 필요없게 됨.
    public boolean isValid(Clock clock) {
        return LocalDateTime.now(clock).isBefore(this.getValidUntil());
    }

    public Long getOrderId() {
        return orderId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getForeignCurrencyAmount() {
        return foreignCurrencyAmount;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public BigDecimal getConvertedAmount() {
        return convertedAmount;
    }

    public LocalDateTime getValidUntil() {
        return validUntil;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "orderId=" + orderId +
                ", currency='" + currency + '\'' +
                ", foreignCurrencyAmount=" + foreignCurrencyAmount +
                ", exchangeRate=" + exchangeRate +
                ", convertedAmount=" + convertedAmount +
                ", validUntil=" + validUntil +
                '}';
    }

}
