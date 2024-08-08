package hoonspring.hellospring6;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {
    public static void main(String[] args){
        /*
         * Collections 클래스의 sort 메소드
         * : 객체지향 디자인 패턴 중 '전략 패턴(Strategy Pattern)'이 적용된 대표전인 예
         */


        // 활용 1. 숫자 오름차순 정령
        List<Integer> scores = Arrays.asList(5, 7, 1, 9, 2, 8);
        Collections.sort(scores);
        scores.forEach(System.out::println);

        // 활용 2. 문자 오름차순 정령(첫글자부터 알파벳 순서대로 정렬)
        List<String> textSortList = Arrays.asList("z","x", "spring", "java", "springboot");
        Collections.sort(textSortList);
        textSortList.forEach(System.out::println);

        /*
         * 활용 3. 문자열의 길이로 오름차순 정령(길이가 같다면 상관없지만 짧은것부터 정렬)
         *
         * : sort라는 기능 맥락(Context)는 두번째 파라미터로 전략 오브젝트에 따라서
         *   자신이 기능을 수행할 알고리즘을 교체해서 사용한다.
         */
        List<String> textLengthSortList = Arrays.asList("z","x", "spring", "java", "springboot");
        Collections.sort(textLengthSortList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        textLengthSortList.forEach(System.out::println);

    }
}
