import java.util.*;

class Solution {
    List<int[]> answer = new ArrayList<>();
    public int[][] solution(int n) {
        move(n,1,3);
        return answer.toArray(int[][]::new);
    }

    public void move(int num, int src, int dest) {
        if (num == 1) {
            answer.add(new int[]{src, dest});
            return;
        }

        move(num - 1, src, 6 - (src + dest));
        answer.add(new int[]{src, dest});
        move(num - 1, 6 - (src + dest), dest);
    }
}