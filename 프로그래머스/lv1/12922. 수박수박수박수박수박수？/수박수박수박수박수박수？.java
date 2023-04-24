import java.util.stream.*;

class Solution {
    public String solution(int n) {
        return IntStream.range(1, n + 1).mapToObj(idx -> (idx & 1) == 0 ? "박" : "수").collect(Collectors.joining());
    }
}