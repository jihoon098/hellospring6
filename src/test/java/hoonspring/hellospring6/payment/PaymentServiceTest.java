package hoonspring.hellospring6.payment;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentServiceTest {

    Clock clock;

    @BeforeEach
    void beforeEach() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    @DisplayName("환율 계산 검증")
     void convertedAmount() {
        testAmount(valueOf(500), valueOf(5_000), this.clock); // 숫자에 '_' 포함 가능. (자릿수 구분을 위해 사용 등 용이)
        testAmount(valueOf(1000), valueOf(10_000), this.clock); // 숫자에 '_' 포함 가능. (자릿수 구분을 위해 사용 등 용이)
        testAmount(valueOf(2000), valueOf(20_000), this.clock); // 숫자에 '_' 포함 가능. (자릿수 구분을 위해 사용 등 용이)
    }

    @Test
    @DisplayName("원화환산금액의 유효시간 계산 검증")
    void validUntil() {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1_000)), this.clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // validUntil이 prepare() 30분 뒤로 설정됐는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        Assertions.assertThat(expectedValidUntil).isEqualTo(payment.getValidUntil());
    }

    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) {
        // 제어할 수 있는 테스트 대역을 이용
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        /*
         * isEqualTo VS isEqualByComparingTo
         *
         * 1) isEqualTo
         *  : 파라미터로 받은 객체의 equals() 메서드를 사용. 값뿐만 아니라 스케일(scale)도 고려
         * 2) isEqualByComparingTo
         *  : 객체의 compareTo() 메서드를 사용. compareTo() 메서드는 값만 비교하고 스케일은 무시
         */
        assertThat(payment.getExchangeRate()).isEqualByComparingTo(exRate);

        // 원화환산금액 계산 검증(환율 X 외환금액)
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}
