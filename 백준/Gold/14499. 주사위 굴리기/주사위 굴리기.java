import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    static class Plane {
        int num = 0;
    }

    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};

    private static Plane T = new Plane();
    private static Plane E = new Plane();
    private static Plane W = new Plane();
    private static Plane S = new Plane();
    private static Plane N = new Plane();
    private static Plane B = new Plane();

    public static void main(String[] args) throws IOException {
        String[] firstLine = br.readLine().split("\\s+");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);
        int x = Integer.parseInt(firstLine[2]);
        int y = Integer.parseInt(firstLine[3]);
        int k = Integer.parseInt(firstLine[4]);

        int[][] board = new int[n][];
        for (int i = 0; i < n; i++) {
            board[i] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }
        int[] commands = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        solution(board, x, y, commands);
        bw.write(res.toString());
        bw.flush();
        bw.close();
    }

    private static void solution(int[][] board, int x, int y, int[] commands) {
        for (int command : commands) {
            int nextX = x + dx[command-1];
            int nextY = y + dy[command-1];

            if (!available(board.length, board[0].length, nextX, nextY)) {
                continue;
            }

            x = nextX;
            y = nextY;
            // 굴리기
            switch (command) {
                case 1: // 동
                    planeSwap(T, E, B, W);
                    break;
                case 2: // 서
                    planeSwap(T, W, B, E);
                    break;
                case 3: // 북
                    planeSwap(T, N, B, S);
                    break;
                default: // 남
                    planeSwap(T, S, B, N);
                    break;
            }

            // 윗면 숫자 저장
            res.append(T.num).append("\n");

            // 바닥면 복사
            if (board[x][y] == 0) {
                board[x][y] = B.num;
            } else {
                B.num = board[x][y];
                board[x][y] = 0;
            }
        }
    }

    private static boolean available(int n, int m, int nextX, int nextY) {
        return 0 <= nextX && nextX < n && 0 <= nextY && nextY < m;
    }

    // p1 -> p2, p2 -> p3, p3->p4, p4 ->p1으로 바꿈
    private static void planeSwap(Plane p1, Plane p2, Plane p3, Plane p4) {
        int temp = p1.num;

        p1.num = p4.num;
        p4.num = p3.num;
        p3.num = p2.num;
        p2.num = temp;
    }
}