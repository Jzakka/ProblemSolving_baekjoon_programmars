import java.util.*;

class Solution {

    Deque<Integer> deque = new LinkedList<>();

    public int solution(int[] stones, int k) {
        for (int i = 0; i < k; i++) {
            int stone = stones[i];

            addDeque(stone);
        }

        int res = Integer.MAX_VALUE;

        for (int i = k; i < stones.length; i++) {
            Integer maxVal = deque.peekFirst();
            res = Math.min(res, maxVal);
            if (stones[i - k] == deque.peekFirst()) {
                deque.pollFirst();
            }
            addDeque(stones[i]);
        }

        Integer maxVal = deque.peekFirst();
        res = Math.min(res, maxVal);

        return res;
    }

    void addDeque(int element) {
        while (!deque.isEmpty() && deque.peekLast() < element) {
            deque.pollLast();
        }
        deque.offerLast(element);
    }
}