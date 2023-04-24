import java.util.*;

class Solution {
    int explosionCount = 0;
    Stack<Integer>[] prizeBox;
    Stack<Integer> basket;
    public int solution(int[][] board, int[] moves) {
        prizeBox = new Stack[board[0].length];
        basket = new Stack<>();

        for (int i = 0; i< prizeBox.length;i++) {
            prizeBox[i] = new Stack<>();
        }

        for (int j = 0; j < board[0].length; j++) {
            for (int i = board.length - 1; i >= 0; i--) {
                if (board[i][j] == 0) {
                    break;
                }
                prizeBox[j].push(board[i][j]);
            }
        }

        for (int x : moves) {
            pick(x);
        }

        return explosionCount;
    }

    public void pick(int x){
        if (prizeBox[x-1].empty()) {
            return;
        }

        int stuff = prizeBox[x-1].pop();

        if (!basket.empty() && basket.peek() == stuff) {
            basket.pop();
            explosionCount += 2;
        } else {
            basket.push(stuff);
        }
    }
}