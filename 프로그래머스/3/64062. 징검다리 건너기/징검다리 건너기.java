import java.util.*;

class Solution {
    Deque<Integer> descentQueue = new ArrayDeque<>();

    public int solution(int[] stones, int k) {
        int l = 0;
        int r = 0;

        for (; r < k; r++) {
            updateDeque(stones[r]);
        }

        int ans = Integer.MAX_VALUE;
        while (r <= stones.length) {
            Integer sectionMax = descentQueue.getFirst();
            ans = Math.min(ans, sectionMax);

            if (sectionMax == stones[l++]) {
                descentQueue.pollFirst();
            }

            if (r < stones.length) {
                updateDeque(stones[r]);
            }
            r++;
        }

        return ans;
    }

    private void updateDeque(int stone) {
        while (!descentQueue.isEmpty() && descentQueue.getLast() < stone) {
            descentQueue.pollLast();
        }
        descentQueue.offerLast(stone);
    }
}