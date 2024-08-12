package hoonspring.hellospring6.learningTest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ClockTest {
    // Clock을 이용해서 LocalDateTime.now ?
    @Test
    void clock() {
        Clock clock = Clock.systemDefaultZone();

        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        System.out.println(dt1);
        // 내부적으로 LocalDateTime.now(Clock.systemDefaultZone())를 호출
        System.out.println(LocalDateTime.now());

        Assertions.assertThat(dt2).isEqualTo(dt1);
    }

    // Clock을 Test에서 사용할 때 내가 원하는 시간을 지정해서 현재 시간을 가져오게 할 수 있는가?
    @Test
    void fixedClock() {
        /*
         * Clock을 사용할때 장점
         * : 단순히 시스템(컴퓨터)의 현재 시간이 아닌 설정한 기준에 따라 시간을 가져 올 때도 유용.
         *
         * 예를 들어,
         * 1. 내가 정한 고정된 시간을 설정(테스트를 위해 사용될 수 있는 Clock.fixed())
         * 2. 다른 나라나 지역의 시간을 기준으로 현재 시간 계산.
         *
         */
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault()); // 시간 셋팅 후 고정시키기.

        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        LocalDateTime dt3 = LocalDateTime.now(clock).plusHours(1);

        Assertions.assertThat(dt1).isEqualTo(dt2);
        Assertions.assertThat(dt3).isEqualTo(dt1.plusHours(1));
    }
}
