import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Queue<int[]> Q = new LinkedList<>();
        int latency;
        int popCount = 0;
        int nextIdx = 0;
        for (latency = 1; popCount < truck_weights.length; latency++) {
            if (!Q.isEmpty() && latency - Q.peek()[1] >= bridge_length) {
                weight += Q.poll()[0];
                popCount++;
            }
            if (popCount == truck_weights.length) {
                return latency;
            }
            else if (Q.size() < bridge_length && nextIdx < truck_weights.length  && weight - truck_weights[nextIdx] >= 0) {
                Q.offer(new int[]{truck_weights[nextIdx], latency});
                weight -= truck_weights[nextIdx++];
            }
        }

        return latency;
    }
}