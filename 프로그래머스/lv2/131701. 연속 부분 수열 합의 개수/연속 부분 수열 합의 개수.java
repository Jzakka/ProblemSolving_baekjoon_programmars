import java.util.*;

//O(n^2)
class Solution {
    public int solution(int[] elements) {
        Set<Integer> sums = new HashSet<>();

        for (int i = 0; i < elements.length; i++) {
            int sum = elements[i];
            sums.add(sum);

            for (int j = (i + 1)% elements.length; j != i; j=(j+1)%elements.length) {
                sum += elements[j];
                sums.add(sum);
            }
        }

        return sums.size();
    }
}