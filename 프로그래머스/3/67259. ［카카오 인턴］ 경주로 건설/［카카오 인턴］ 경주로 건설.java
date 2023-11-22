import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    int[] dx = {-1, 0, 0, 1};
    int[] dy = {0, -1, 1, 0};
    int[][][] DP;
    public int solution(int[][] board) {
        int n = board.length;
        DP = new int[n][n][4];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 4; k++) {
                    DP[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        dijkstra(board);

        return Math.min(DP[n - 1][n - 1][2], DP[n - 1][n - 1][3]);
    }

    private void dijkstra(int[][] board) {
        DP[0][0][0] =DP[0][0][1] =DP[0][0][2] =DP[0][0][3] =0;
        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.comparingInt(a -> DP[a[0]][a[1]][a[2]]));
        PQ.add(new int[]{0, 0, 2, 0});
        PQ.add(new int[]{0, 0, 3, 0});

        while (!PQ.isEmpty()) {
            int[] cur = PQ.poll();

            int x = cur[0];
            int y = cur[1];
            int vector = cur[2];
            int cost = cur[3];

//            System.out.println("x = " + x);
//            System.out.println("y = " + y);
//            System.out.println("vector = " + vector);
//            System.out.println();

            if (cost > DP[x][y][vector]) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                int movedCost;
                if (available(nx, ny, board) && DP[nx][ny][i] > (movedCost = addCost(x, y, vector, i))) {
                    DP[nx][ny][i] = movedCost;
                    PQ.add(new int[]{nx, ny, i, movedCost});
                }
            }
        }

    }

    private int addCost(int x, int y, int vector, int nextVector) {
        return DP[x][y][vector] + (vector == nextVector ? 100 : 600);
    }

    private boolean available(int x, int y, int[][] board) {
        int n = board.length;

        return 0 <= x && x < n && 0 <= y && y < n && board[x][y] == 0;
    }
}