import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws Exception {
        String[] nm = br.readLine().split("\\s+");
        int n = Integer.parseInt(nm[0]);
        int m = Integer.parseInt(nm[1]);

        int[][] board = new int[n][];
        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        solution(board);

        printRes();
    }

    static  class Coordinate{
        int x,y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void solution(int[][] board) {
        int[][] DP = new int[board.length][board[0].length];
        Arrays.stream(DP).forEach(arr -> Arrays.fill(arr, -1));

        DP[board.length-1][board[0].length-1] = 1;

        int ans = DFS(board, DP);
        res.append(ans);
    }

    private static int DFS(int[][] board, int[][] DP) {
        Stack<Coordinate> stk = new Stack<>();
        stk.push(new Coordinate(0, 0));

        while (!stk.isEmpty()) {
            Coordinate top = stk.peek();
            if (DP[top.x][top.y] == -1) {
                DP[top.x][top.y] = 0;

                for (int i = 0; i < 4; i++) {
                    int nextX = top.x + dx[i];
                    int nextY = top.y + dy[i];

                    if (available(board.length, board[0].length, nextX, nextY) && board[top.x][top.y] > board[nextX][nextY]) {
                        stk.push(new Coordinate(nextX, nextY));
                    }
                }
            } else if (DP[top.x][top.y] == 0) {
                for (int i = 0; i < 4; i++) {
                    int nextX = top.x + dx[i];
                    int nextY = top.y + dy[i];

                    if (available(board.length, board[0].length, nextX, nextY) && board[top.x][top.y] > board[nextX][nextY]) {
                        DP[top.x][top.y] += DP[nextX][nextY] != -1 ? DP[nextX][nextY] : 0;
                    }
                }
                stk.pop();
            } else {
                stk.pop();
            }
        }

        return DP[0][0];
    }

    private static boolean available(int n, int m, int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}