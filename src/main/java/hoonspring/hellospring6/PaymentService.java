package hoonspring.hellospring6;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class PaymentService {

    private final ExRateProvider provider;

    public PaymentService(ExRateProvider provider) {
        this.provider = provider;
    }

    public Payment prepare(Long orderId, String currency , BigDecimal foreignCurrencyAmount) throws IOException {

        BigDecimal exRate = provider.getExRate(currency);
        BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exRate);
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, foreignCurrencyAmount, exRate, convertedAmount, validUntil);
    }

}
