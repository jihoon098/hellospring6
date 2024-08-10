package hoonspring.hellospring6.exRate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

/*
 * record 타입
 * : Java 16에서 도입되어 데이터를 담을 수 있는 용도의 클래스를 간편하게 만들 수 있는 기능.
 *   주의할 점으로 레코드는 '불변 데이터 객체'로 한번 값을 넣으면 수정이 불가능.
 *
 * @JsonIgnoreProperties(ignoreUnknown = true)
 * : Json 데이터를 처리 시에, Json에 존재하지 않는 속성을 무시하도록 설정.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ExRateData(String result, Map<String, BigDecimal> rates) {
}
