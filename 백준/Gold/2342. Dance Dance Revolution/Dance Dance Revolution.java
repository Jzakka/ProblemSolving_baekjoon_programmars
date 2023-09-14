import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] command = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(command);

        printRes();
    }

    private static void solution(int[] command) {
        int[][] weight = new int[5][5];
        for (int i = 0; i < weight.length; i++) {
            weight[i][0] = 2;
            weight[0][i] = 2;
        }

        for (int i = 1; i < weight.length; i++) {
            for (int j = 1; j < weight.length; j++) {
                weight[i][j] = power(i, j);
            }
        }

        // DP[i][r][l] : i번째에 오른발이 r, 왼발이 l에 있을 떄, 최소 힘
        int[][][] DP = new int[command.length][5][5];
        Arrays.stream(DP).forEach(row -> Arrays.stream(row).forEach(col -> Arrays.fill(col, Integer.MAX_VALUE)));
        DP[0][0][0] = 0;

        for (int i = 0; i < command.length - 1; i++) {
            int nextStep = command[i];
            for (int r = 0; r < 5; r++) {
                for (int l = 0; l < 5; l++) {
                    if (DP[i][r][l] != Integer.MAX_VALUE) {
                        if (nextStep != l) {
                            DP[i + 1][nextStep][l] = Math.min(DP[i + 1][nextStep][l], DP[i][r][l] + weight[r][nextStep]);
                        }
                        if (nextStep != r) {
                            DP[i + 1][r][nextStep] = Math.min(DP[i + 1][r][nextStep], DP[i][r][l] + weight[l][nextStep]);
                        }
                    }
                }
            }
        }

        int ans = Arrays.stream(DP[DP.length - 1]).flatMapToInt(Arrays::stream).min().getAsInt();
        res.append(ans);
    }

    private static int power( int from, int to) {
        if (Math.abs(from - to) == 1 || Math.abs(from - to) == 3) {
            return 3;
        } else if (Math.abs(from - to) == 2) {
            return 4;
        }
        return 1;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}