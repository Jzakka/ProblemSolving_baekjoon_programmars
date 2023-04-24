import java.util.*;
import java.util.stream.Collectors;

class Solution {
    public long solution(long n) {
        return Long.valueOf(String
                .valueOf(n)
                .chars()
                .mapToObj(c -> (char) c)
                .sorted(Collections.reverseOrder())
                .map(Object::toString)
                .collect(Collectors.joining()));
    }
}