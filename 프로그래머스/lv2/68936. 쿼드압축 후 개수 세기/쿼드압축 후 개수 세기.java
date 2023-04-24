class Solution {
    int[][] board;
    int[] counts = {0, 0};//counts[0] 0의 개수, counts[1] 1의 개수

    public int[] solution(int[][] arr) {
        board = arr;
        divide(0, 0, board.length, board.length);
        return counts;
    }

    private void divide(int x1, int y1, int x2, int y2) {
        int sum = 0;
        for (int x = x1; x < x2; x++) {
            for (int y = y1; y < y2; y++) {
                sum += board[x][y];
            }
        }
        if (sum == 0) {
            counts[0]++;
        } else if (sum == (x2 - x1) * (x2 - x1)) {
            counts[1]++;
        } else {
            divide(x1, y1, (x1 + x2) / 2, (y1 + y2) / 2);
            divide(x1, (y1 + y2) / 2, (x1 + x2) / 2, y2);
            divide((x1 + x2) / 2, y1, x2, (y1 + y2) / 2);
            divide((x1 + x2) / 2, (y1 + y2) / 2, x2, y2);
        }
    }
}