import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int m = triangle.length;

        for (int i = 1; i < m; i++) {
            int n = i + 1;
            triangle[i][0] += triangle[i - 1][0];
            for (int j = 1; j < n - 1; j++) {
                triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }
            triangle[i][n - 1] += triangle[i - 1][n - 2];
        }

        return Arrays.stream(triangle[m - 1]).max().orElse(-1);
    }
}