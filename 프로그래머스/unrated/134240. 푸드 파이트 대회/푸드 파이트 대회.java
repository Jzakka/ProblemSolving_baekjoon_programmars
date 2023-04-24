import java.util.stream.Collectors;
import java.util.stream.IntStream;
class Solution {
    public String solution(int[] food) {
        String collect = IntStream
                .range(1, food.length)
                .mapToObj(i -> IntStream
                        .range(0, food[i]/2)
                        .mapToObj(j -> new StringBuilder())
                        .reduce(new StringBuilder(), (acc, cur) -> acc.append(i)))
                .collect(Collectors.joining());
        return collect +
                "0" +
                new StringBuilder(collect).reverse();
    }
}