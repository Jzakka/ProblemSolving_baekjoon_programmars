import java.util.stream.*;

class Solution {
    public String solution(String s) {
        return s
                .chars()
                .boxed()
                .sorted((Integer a, Integer b)->{
                    if ((a >= 'a' && a <= 'z' && b >= 'a' && b <= 'z')
                    || a >= 'A' && a <= 'Z' && b >= 'A' && b <= 'Z') {
                        return b - a;
                    }
                    else if ((a >= 'a' && a <= 'z' && b >= 'A' && b <= 'Z')) {
                        return -1;
                    }
                    return 1;
                })
                .mapToInt(Integer::intValue)
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());
    }
}