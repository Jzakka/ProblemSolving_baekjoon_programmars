import java.util.*;

class Solution {
    Set<Integer> visited = new HashSet<>();
    int[][] dungeons;
    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;

        return search(0, k);
    }

    private int search(int count, int k) {
        int result = count;
        for (int i = 0; i < dungeons.length; i++) {
            int minEnergy = dungeons[i][0];
            int costEnergy = dungeons[i][1];

            if (!visited.contains(i) && k >= minEnergy) {
                visited.add(i);
                result = Math.max(result, search(count + 1, k - costEnergy));
                visited.remove(i);
            }
        }
        return result;
    }
}