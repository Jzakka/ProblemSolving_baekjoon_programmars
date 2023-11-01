import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int n = getInts()[0];

        int[][] maze = new int[n][];

        for (int i = 0; i < n; i++) {
            maze[i] = getInts();
        }

        solution(maze);

        printRes();
    }

    private static void solution(int[][] maze) {
        int n = maze.length;
        int m = maze[0].length;

        for (int i = 1; i < n; i++) {
            maze[i][0] += maze[i - 1][0];
        }
        for (int j = 1; j < m; j++) {
            maze[0][j] += maze[0][j - 1];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                maze[i][j] = Math.max(maze[i - 1][j - 1], Math.max(maze[i - 1][j], maze[i][j - 1])) + maze[i][j];
            }
        }

        res.append(maze[n - 1][m - 1]);
    }


    private static int[] getInts() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static long[] getLongs() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}