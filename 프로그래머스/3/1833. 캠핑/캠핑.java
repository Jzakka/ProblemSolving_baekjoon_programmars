import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(int n, int[][] data) {
        int[][] compressed = compress(data);

        int r = compressed.length;
        int c = compressed[0].length;

        for (int i = 1; i < r; i++) {
            compressed[i][0] += compressed[i - 1][0];
        }
        for (int i = 1; i < c; i++) {
            compressed[0][i] += compressed[0][i - 1];
        }
        for (int i = 1; i < r; i++) {
            for (int j = 1; j < c; j++) {
                compressed[i][j] += compressed[i - 1][j] + compressed[i][j - 1] - compressed[i - 1][j - 1];
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int[] coord1 = data[i];
            for (int j = i+1; j < n; j++) {
                int[] coord2 = data[j];

                if (coord1[0] != coord2[0] && coord1[1] != coord2[1] && available(coord1, coord2, compressed)) {
                    ans++;
                }
            }
        }

        return ans;
    }

    private boolean available(int[] coord1, int[] coord2, int[][] S) {
        int x1 = Math.min(coord1[0], coord2[0]);
        int y1 = Math.min(coord1[1], coord2[1]);
        int x2 = Math.max(coord1[0], coord2[0]);
        int y2 = Math.max(coord1[1], coord2[1]);

        int cnts = S[x2-1][y2-1] - S[x1][y2-1] - S[x2-1][y1] + S[x1][y1];

        return cnts == 0;
    }

    private int[][] compress(int[][] data) {
        Map<Integer, Integer> xMap = new HashMap<>();
        Map<Integer, Integer> yMap = new HashMap<>();
        int[] xPos = Arrays.stream(data).mapToInt(d -> d[0]).distinct().sorted().toArray();
        int[] yPos = Arrays.stream(data).mapToInt(d -> d[1]).distinct().sorted().toArray();

        int n = xPos.length;
        int m = yPos.length;

        for (int i = 0; i < n; i++) {
            int realPos = xPos[i];
            xMap.put(realPos, i);
        }
        for (int i = 0; i < m; i++) {
            int realPos = yPos[i];
            yMap.put(realPos, i);
        }

        int[][] matrix = new int[n][m];

        for (int[] coord : data) {
            int x = coord[0];
            int y = coord[1];

            coord[0] = xMap.get(x);
            coord[1] = yMap.get(y);

            matrix[coord[0]][coord[1]] = 1;
        }

        return matrix;
    }
}