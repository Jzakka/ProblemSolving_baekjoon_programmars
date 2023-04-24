import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public String solution(int[] numbers) {
        if (Arrays.stream(numbers).allMatch(i -> i == 0)) {
            return "0";
        }
        Integer[] integers = Arrays.stream(numbers).boxed().toArray(Integer[]::new);
        Arrays.sort(integers, this::compare);
        return Arrays.stream(integers).map(Object::toString).collect(Collectors.joining());
    }

    public int compare(Integer num1, Integer num2) {
        String numStr1 = num1.toString();
        String numStr2 = num2.toString();
        int i = 0;

        for (; i < numStr1.length() && i < numStr2.length(); i++) {
            if (numStr1.charAt(i) < numStr2.charAt(i)) {
                return 1;
            } else if (numStr1.charAt(i) > numStr2.charAt(i)) {
                return -1;
            }
        }

        if (numStr1.length() != numStr2.length()) {
            if (i == numStr1.length()) {
                for (int j = i; j < numStr2.length(); j++) {
                    if (numStr2.charAt(j) < numStr2.charAt(j % i)) {
                        return -1;
                    } else if (numStr2.charAt(j) > numStr2.charAt(j % i)) {
                        return 1;
                    }
                }
                return numStr2.charAt(0) - numStr2.charAt(1);
            } else {
                for (int j = i; j < numStr1.length(); j++) {
                    if (numStr1.charAt(j) < numStr1.charAt(j % i)) {
                        return 1;
                    } else if (numStr1.charAt(j) > numStr1.charAt(j % i)) {
                        return -1;
                    }
                }
                return -(numStr1.charAt(0) - numStr1.charAt(1));
            }

        }
        return 0;
    }
}