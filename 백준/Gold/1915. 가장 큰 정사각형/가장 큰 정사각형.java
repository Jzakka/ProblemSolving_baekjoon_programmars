import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        String[] inputs = br.readLine().split("\\s+");

        int n = Integer.parseInt(inputs[0]);
        int m = Integer.parseInt(inputs[1]);

        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                char cell = line.charAt(j);
                matrix[i][j] = cell == '1' ? 1 : 0;
            }
        }

        solution(matrix);

        printRes();
    }

    private static void solution(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        int[][] DP = new int[n][m];
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            if (matrix[i][0] == 1) {
                DP[i][0] = 1;
                maxArea = 1;
            }
        }

        for (int i = 0; i < m; i++) {
            if (matrix[0][i] == 1) {
                DP[0][i] = 1;
                maxArea = 1;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 1) {
                    DP[i][j] = Math.min(DP[i - 1][j - 1], Math.min(DP[i][j - 1], DP[i - 1][j])) + 1;
                }
                maxArea = Math.max(maxArea, DP[i][j] * DP[i][j]);
            }
        }
        res.append(maxArea);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
abcdyx
 */