import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemies) {
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Comparator.reverseOrder());


        for (int i = 0; i < enemies.length; i++) {
            int enemy = enemies[i];

            pQ.add(enemy);
            if (n < enemy && k > 0) {
                while (n < enemy && !pQ.isEmpty() && k > 0) {
                    n += pQ.poll();
                    k--;
                }
            }
            if (n < enemy) {
                return i;
            }
            n -= enemy;
        }

        return enemies.length;
    }
}