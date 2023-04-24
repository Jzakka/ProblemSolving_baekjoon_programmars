class Solution {
    public int solution(int[][] board) {
        int maxLen = board[0][0];

        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                if (board[i][j] == 1) {
                    board[i][j] = Math.min(board[i - 1][j], Math.min(board[i][j - 1], board[i - 1][j - 1])) + 1;
                    maxLen = Math.max(maxLen, board[i][j]);
                }
            }
        }

        return maxLen * maxLen;
    }
}