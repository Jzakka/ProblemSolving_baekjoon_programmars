class Solution {
    public int solution(String[] board) {
        int oCnt = 0;
        int xCnt = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char c = board[i].charAt(j);
                if (c == 'O') {
                    oCnt++;
                } else if (c == 'X') {
                    xCnt++;
                }
            }
        }
        int diff = oCnt - xCnt;
        if (diff > 1 || diff < 0) {
            return 0;
        }

        boolean[] bingoCheck = bingoCheck(board);
        boolean oBingo = bingoCheck[0];
        boolean xBingo = bingoCheck[1];

        if (oBingo && xBingo) {
            return 0;
        }

        if (oBingo && oCnt <= xCnt) {
            return 0;
        }

        if (xBingo && diff>=1) {
            return 0;
        }

        if (oCnt + xCnt == 9 && diff != 1) {
            return 0;
        }

        return 1;
    }

    private boolean[] bingoCheck(String[] board) {
        boolean oBingo = false;
        boolean xBingo = false;

        if (board[0].charAt(0) == board[0].charAt(1) && board[0].charAt(1) == board[0].charAt(2)) {
            oBingo = board[0].charAt(0) == 'O';
            xBingo = board[0].charAt(0) == 'X';
        }

        if (board[0].charAt(0) == board[1].charAt(0) && board[1].charAt(0) == board[2].charAt(0)) {
            oBingo |= board[0].charAt(0) == 'O';
            xBingo |= board[0].charAt(0) == 'X';
        }

        if (board[2].charAt(0) == board[2].charAt(1) && board[2].charAt(1) == board[2].charAt(2)) {
            oBingo |= board[2].charAt(0) == 'O';
            xBingo |= board[2].charAt(0) == 'X';
        }

        if (board[0].charAt(2) == board[1].charAt(2) && board[1].charAt(2) == board[2].charAt(2)) {
            oBingo |= board[0].charAt(2) == 'O';
            xBingo |= board[0].charAt(2) == 'X';
        }

        if (board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)) {
            oBingo |= board[0].charAt(0) == 'O';
            xBingo |= board[0].charAt(0) == 'X';
        }

        if (board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)) {
            oBingo |= board[0].charAt(2) == 'O';
            xBingo |= board[0].charAt(2) == 'X';
        }

        if (board[1].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[1].charAt(2)) {
            oBingo |= board[1].charAt(0) == 'O';
            xBingo |= board[1].charAt(0) == 'X';
        }

        if (board[0].charAt(1) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(1)) {
            oBingo |= board[0].charAt(1) == 'O';
            xBingo |= board[1].charAt(1) == 'X';
        }

        return new boolean[]{oBingo, xBingo};
    }
}