package hoonspring.hellospring6.payment;

import hoonspring.hellospring6.exRate.WebApiExRateProvider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메소드가 요구사항 3가지를 잘 충족했는지 검증")
    void prepare() throws IOException {
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider());

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // 환율정보 검증
        Assertions.assertThat(payment.getExchangeRate()).isNotNull();

        // 원화환산금액 계산 검증(환율 X 외환금액)
        Assertions.assertThat(payment.getConvertedAmount())
                .isEqualTo(payment.getExchangeRate().multiply(payment.getForeignCurrencyAmount()));

        // 원화환산금액의 유효시간 계산 검증
        Assertions.assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
        Assertions.assertThat(payment.getValidUntil()).isBefore(LocalDateTime.now().plusMinutes(30));

    }
}
