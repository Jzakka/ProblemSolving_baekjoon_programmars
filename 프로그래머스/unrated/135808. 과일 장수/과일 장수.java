import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        Arrays.sort(score);

        int res = 0;
        for (int i = score.length - m; i >= 0; i -= m) {
            res += score[i] * m;
        }
        return res;
    }
}