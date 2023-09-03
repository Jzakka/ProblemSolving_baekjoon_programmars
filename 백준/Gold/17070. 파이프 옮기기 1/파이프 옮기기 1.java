import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] board = new int[n][];
        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(n, board);

        printRes();
    }

    private static void solution(int n, int[][] board) {
        int[] tail = {0, 0};
        int[] head = {0, 1};

        int ans = dfs(n, board, head, tail);

        res.append(ans);
    }

    private static int dfs(int n, int[][] board, int[] head, int[] tail) {
        if (head[0] == n - 1 && head[1] == n - 1) {
            return 1;
        }

        int[][] nextHeads = getNextHeads(board, head, tail);

        int sum = 0;
        for (int[] nextHead : nextHeads) {
            if (available(n, board, nextHead, head)) {
                sum += dfs(n, board, nextHead, head);
            }
        }
        return sum;
    }

    private static boolean available(int n, int[][] board, int[] head, int[] tail) {
        int headX = head[0];
        int headY = head[1];
        int tailX = tail[0];
        int tailY = tail[1];

        if (!(0 <= headX && headX < n && 0 <= headY && headY < n && board[headX][headY] == 0)) {
            return false;
        }

        if (headX != tailX && headY != tailY) {
            return board[headX][tailY] == 0 && board[tailX][headY] == 0;
        }

        return true;
    }

    private static int[][] getNextHeads(int[][] board, int[] head, int[] tail) {
        if(head[0] == tail[0]){ // x좌표가 같을 때 --> 가로임
            return new int[][]{{head[0], head[1] + 1}, {head[0] + 1, head[1] + 1}};
        }
        if (head[1] == tail[1]) {
            return new int[][]{{head[0] + 1, head[1]}, {head[0] + 1, head[1] + 1}};
        }
        return new int[][]{{head[0], head[1] + 1}, {head[0] + 1, head[1]}, {head[0] + 1, head[1] + 1}};
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}