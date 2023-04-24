import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> foods = new PriorityQueue<>();
        Arrays.stream(scoville).forEach(foods::add);

        int count = 0;
        while (foods.peek() < K) {
            if (foods.size() == 1) {
                return -1;
            }

            int smallest = foods.poll();
            int second = foods.poll();
            foods.add(smallest + second * 2);
            count++;
        }
        return count;
    }
}