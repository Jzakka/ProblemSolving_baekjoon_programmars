import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(a -> a[0]));
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] route : routes) {
            int start = route[0];
            int end = route[1];

            if (!pq.isEmpty()) {
                Integer lowestEnd = pq.peek();
                if (lowestEnd < start) {
                    answer++;
                    pq.clear();
                }
            }
            pq.add(end);
        }

        if (!pq.isEmpty()) {
            answer++;
        }


        return answer;
    }
}