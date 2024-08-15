package hoonspring.hellospring6.payment;

import hoonspring.hellospring6.TestPaymentConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;

import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;

/*
 * @ExtendWith(SpringExtension.class)
 *  JUnit5 에서 Spring이 제공하는 기능을 사용할 수 있도록 지원하는 어노테이션.
 *
 * @ContextConfiguration(classes = ?)
 * : JUnit에서 스프링 통합 테스트를 설정할 때 사용하는 어노테이션.
 *   classes 속성으로 지정한 구성 정보 클래스(Configuration 클래스)를 기반으로 스프링 컨테이너를 초기화.
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
public class PaymentServiceSpringTest {

    @Autowired public PaymentService paymentService;
    @Autowired public ExRateProviderStub exRateProviderStub;
    @Autowired public Clock clock;

    @Test
    void convertedAmount() {
        // exRate: 1000
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExchangeRate()).isEqualByComparingTo(valueOf(1_000));
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(valueOf(10_000));

        // exRate: 500
        exRateProviderStub.setExRate(valueOf(500));
        Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment2.getExchangeRate()).isEqualByComparingTo(valueOf(500));
        assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(valueOf(5_000));
    }

    @Test
    @DisplayName("원화환산금액의 유효시간 계산 검증")
    void validUntil() {
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        // validUntil이 prepare() 30분 뒤로 설정됐는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        Assertions.assertThat(expectedValidUntil).isEqualTo(payment.getValidUntil());
    }
}