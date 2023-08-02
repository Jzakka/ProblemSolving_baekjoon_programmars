import java.util.*;

class Solution {
    class Block {
        int idx = 0;
        int[][] points = new int[4][2];
        int[][] depresses = new int[2][2];
        boolean valid;

        Block(int i, int j){
            addPoint(i,j);
        }

        void addPoint(int i, int j){
            points[idx][0] = i;
            points[idx++][1] = j;

            if (idx == 4) {
                validate();
            }
        }

        void validate() {
            int x0 = points[0][0];
            int x1 = points[1][0];
            int x2 = points[2][0];
            int x3 = points[3][0];

            int y0 = points[0][1];
            int y1 = points[1][1];
            int y2 = points[2][1];
            int y3 = points[3][1];

            if (x0 + 1 == x1 && y0 == y1) {
                if (x0 + 2 == x2 && y0 == y2 && x0 + 2 == x3 && y0 + 1 == y3 ) {
                    depresses[0][0] = x0;
                    depresses[0][1] = y0 + 1;
                    depresses[1][0] = x0 + 1;
                    depresses[1][1] = y0 + 1;
                    valid = true;
                    return;
                }
                if (x0 + 1 == x2 && y0 + 1 == y2 && x0 + 1 == x3 && y0 + 2 == y3 ) {
                    depresses[0][0] = x0;
                    depresses[0][1] = y0 + 1;
                    depresses[1][0] = x0;
                    depresses[1][1] = y0 + 2;
                    valid = true;
                    return;
                }
                if (x0 + 2 == x2 && y0 - 1 == y2 && x0 + 2 == x3 && y0 == y3 ) {
                    depresses[0][0] = x0;
                    depresses[0][1] = y0 - 1;
                    depresses[1][0] = x0 + 1;
                    depresses[1][1] = y0 - 1;
                    valid = true;
                    return;
                }
            }

            if (x0 + 1 == x1 && y0 - 2== y1) {
                if (x0 + 1 == x2 && y0 - 1 == y2 && x0 + 1 == x3 && y0 == y3 ) {
                    depresses[0][0] = x0;
                    depresses[0][1] = y0 - 2;
                    depresses[1][0] = x0;
                    depresses[1][1] = y0 - 1;
                    valid = true;
                    return;
                }
            }

            if (x0 + 1 == x1 && y0 - 1== y1) {
                if (x0 + 1 == x2 && y0  == y2 && x0 + 1 == x3 && y0 + 1 == y3 ) {
                    depresses[0][0] = x0;
                    depresses[0][1] = y0 - 1;
                    depresses[1][0] = x0;
                    depresses[1][1] = y0 + 1;
                    valid = true;
                }
            }
        }
    }

    Map<Integer, Block> blocks = new HashMap<>();

    int[][] board;
    public int solution(int[][] board) {
        this.board = board;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int num = board[i][j];
                if (num != 0) {
                    mapAdd(num, i, j);
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int num = board[i][j];
                Block block = blocks.get(num);

                if (block != null && block.valid) {
                    int x1 = block.depresses[0][0];
                    int y1 = block.depresses[0][1];
                    int x2= block.depresses[1][0];
                    int y2 = block.depresses[1][1];

                    if (noCeil(x1,y1) && noCeil(x2,y2)) {
                        cnt++;
                        for (int[] p : block.points) {
                            board[p[0]][p[1]] = 0;
                        }
                        j = -1;
                    }
                }
            }
        }
        return cnt;
    }

    private boolean noCeil(int x, int y) {
        for (int i = 0; i <= x; i++) {
            if (board[i][y] != 0) {
                return false;
            }
        }
        return true;
    }

    void mapAdd(int blockNo, int i, int j) {
        if (blocks.containsKey(blockNo)) {
            blocks.get(blockNo).addPoint(i, j);
            return;
        }
        blocks.put(blockNo, new Block(i, j));
    }
}