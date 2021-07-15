package main;

import java.util.*;
import java.util.stream.Collectors;

public class Kata {
    public static double findUniq(double[] arr) {
        List<Double> doubleList = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Map<Double, Long> checkedMap = new HashMap<>();
        for (Double tempNum : doubleList) {
            Long num = checkedMap.get(tempNum);
            if (num != null)
                checkedMap.put(tempNum, ++num);
            else
                checkedMap.put(tempNum, 1L);
        }
        Set<Double> keySet = checkedMap.keySet();
        for (Double key : keySet) {
            if (checkedMap.get(key) == 1L) {
                return key;
            }
        }
        return 0;
    }
}
