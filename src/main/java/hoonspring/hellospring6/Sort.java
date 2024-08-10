package hoonspring.hellospring6;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {

    // 문자열의 길이로 오름차순 정령(길이가 같다면 상관없지만 짧은것부터 정렬)
    public List<String> sortByLength(List<String> list){
        list.sort((o1, o2) -> o1.length() - o2.length());
        return list;
    }
}
