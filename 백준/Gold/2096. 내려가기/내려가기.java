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
        int[][] maxScores = new int[n][3];
        int[][] minScores = new int[n][3];

        for (int i = 0; i < 3; i++) {
            maxScores[0][i] = minScores[0][i] = board[0][i];
        }

        for (int i = 1; i < n; i++) {
            maxScores[i][0] = Math.max(maxScores[i - 1][0], maxScores[i - 1][1]) + board[i][0];
            maxScores[i][1] = Math.max(maxScores[i - 1][0], Math.max(maxScores[i - 1][1], maxScores[i - 1][2])) + board[i][1];
            maxScores[i][2] = Math.max(maxScores[i - 1][1], maxScores[i - 1][2]) + board[i][2];

            minScores[i][0] = Math.min(minScores[i - 1][0], minScores[i - 1][1]) + board[i][0];
            minScores[i][1] = Math.min(minScores[i - 1][0], Math.min(minScores[i - 1][1], minScores[i - 1][2])) + board[i][1];
            minScores[i][2] = Math.min(minScores[i - 1][1], minScores[i - 1][2]) + board[i][2];
        }

        int maxScore = Arrays.stream(maxScores[n - 1]).max().getAsInt();
        int minScore = Arrays.stream(minScores[n - 1]).min().getAsInt();

        res.append(maxScore).append(" ").append(minScore);
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}