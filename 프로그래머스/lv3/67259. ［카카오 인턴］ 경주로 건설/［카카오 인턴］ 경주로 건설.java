import java.util.*;

class Solution {
    int[][][] costs;
    int[][] board;
    int n;
    private static final int W = 0;
    private static final int S = 1;
    private static final int E = 2;
    private static final int N = 3;

    private final static int[] dx = {0, -1, 1, 0};
    private final static int[] dy = {1, 0, 0, -1};
    private final static int[] dir = {E, N, S, W};

    public int solution(int[][] board) {
        this.n = board.length;
        costs = new int[n][n][4];
        this.board = board;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                costs[i][j][W] = Integer.MAX_VALUE;
                costs[i][j][S] = Integer.MAX_VALUE;
                costs[i][j][E] = Integer.MAX_VALUE;
                costs[i][j][N] = Integer.MAX_VALUE;
            }
        }
        costs[0][0][S] = 0;
        costs[0][0][W] = 0;
        costs[0][0][E] = 0;
        costs[0][0][N] = 0;

        if (board[1][0] == 0) {
            costs[1][0][S] = 100;
            dfs(1, 0, S);
        }
        if (board[0][1] == 0) {
            costs[0][1][E] = 100;
            dfs(0, 1, E);
        }

        return Arrays.stream(costs[n - 1][n - 1]).min().orElseThrow();
    }

    private void dfs(int x, int y, int direction) {
        List<int[]> nexts = getNexts(x, y, direction);

        for (int[] next : nexts) {
            dfs(next[0], next[1], next[2]);
        }
    }

    private List<int[]> getNexts(int x, int y, int direction) {
        List<int[]> nexts = new ArrayList<>();
        int cost = costs[x][y][direction];
        int nextCost;

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            int nextDir = dir[i];

            if (inRange(nextX, nextY) && board[nextX][nextY] == 0
                    && cost + (nextCost = getAdditional(x, nextX, direction)) <= costs[nextX][nextY][nextDir]) {
                costs[nextX][nextY][nextDir] = cost + nextCost;
                nexts.add(new int[]{nextX, nextY, nextDir});
            }
        }


        return nexts;
    }

    private boolean inRange(int nextX, int nextY) {
        return nextX >= 0 && nextY >= 0 && nextX < n && nextY < n;
    }

    private int getAdditional(int curX, int nextX, int direction) {
        int xDiff = curX - nextX;

        int cost = 100;
        if (xDiff == 0) { // y방향으로 움직였다
            if (direction == S || direction == N) { // 근데 현재 방향이 동서이면 코너 추가값 들어감
                cost += 500;
            }
        } else { // x방향으로 움직였다
            if (direction == W || direction == E) { // 근데 현재 방향이 남북이면 코너 추가값 들어감
                cost += 500;
            }
        }
        return cost;
    }
}