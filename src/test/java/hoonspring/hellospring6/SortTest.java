package hoonspring.hellospring6;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class SortTest {

    Sort sort;

    // 모든 테스트에 공통적으로 필요한 준비사항이 있다면 @BeforeEach 사용
    @BeforeEach
    void beforeEach() {
        sort = new Sort();
        /*
         * JUnit의 기본 동작 방식
         * : 모든 테스트는 다른 테스트에 영향을 받지않는 별개의 독립적인 실행이 되어야하기 때문에,
         *   테스트마다 새로운 클래스의 인스턴스가 만들어진다!
         */
        System.out.println(this);
    }

    @Test
    void sort() {
        // 1. 테스트를 실행하기위한 준비
        Sort sort = new Sort();

        // 2. 테스트할 기능을 실행
        List<String> list = sort.sortByLength(Arrays.asList("bb", "a"));

        // 3. 실행 결과에 대한 검증
        Assertions.assertThat(list).isEqualTo(List.of("a","bb"));
    }

    @Test
    void sort3Items() {
        List<String> list = sort.sortByLength(Arrays.asList("aa", "ccc", "b"));

        Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }

    @Test
    void sortAlreadySorted() {
        List<String> list = sort.sortByLength(Arrays.asList("b", "aa", "ccc"));

        Assertions.assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }
}
