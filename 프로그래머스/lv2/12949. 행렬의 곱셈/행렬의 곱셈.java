class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        int m, k, n;
        m = arr1.length;
        k = arr2.length;
        n = arr2[0].length;

        int[][] answer = new int[m][n];
        int r;
        for (int t = 0; t < k; t++) {
            for (int i = 0; i < m; i++) {
                r = arr1[i][t];
                for (int j = 0; j < n; j++) {
                    answer[i][j] += r * arr2[t][j];
                }
            }
        }

        return answer;
    }
}