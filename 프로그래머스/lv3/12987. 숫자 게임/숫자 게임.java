import java.util.*;
import java.util.stream.Collectors;

class Solution {
    TreeSet<Integer> bSet;
    Map<Integer, Integer> count = new HashMap<>();

    public int solution(int[] A, int[] B) {
        for (int b : B) {
            if (count.containsKey(b)) {
                count.put(b, count.get(b) + 1);
            } else {
                count.put(b, 1);
            }
        }
        bSet = Arrays.stream(B).boxed().collect(Collectors.toCollection(TreeSet::new));

        int score = 0;

        for (int a : A) {
            Integer higher = bSet.higher(a);
            if (higher != null) {
                count.put(higher, count.get(higher) - 1);
                if (count.get(higher) == 0) {
                    bSet.remove(higher);
                }
                score++;
            } else {
                Integer first = bSet.first();
                count.put(first, count.get(first) - 1);
                if (count.get(first) == 0) {
                    bSet.remove(first);
                }
            }
        }

        return score;
    }
}