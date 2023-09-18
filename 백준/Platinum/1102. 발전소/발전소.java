import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[][] repair = new int[n][];
        for (int i = 0; i < n; i++) {
            repair[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        String initStatus = br.readLine();
        int p = Integer.parseInt(br.readLine());

        solution(repair, initStatus, p);

        printRes();
    }

    private static void solution(int[][] repair, String initStatus, int p) {
        int n = repair.length;
        int bitStatus = 0;
        int running = 0;
        for (int i = 0; i < initStatus.length(); i++) {
            boolean bit = initStatus.charAt(i) == 'Y';
            if (bit) {
                running++;
                bitStatus += 1 << i;
            }
        }

        if (running >= p) {
            res.append(0);
            return;
        }

        int DP[][] = new int[n + 1][1 << n];
        Arrays.stream(DP).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));
        DP[running][bitStatus] = 0;

        for (int i = running; i < p; i++) {
            for (int j = 0; j < DP[0].length; j++) {
                if (DP[i][j] == Integer.MAX_VALUE) {
                    continue;
                }
                updateDP(repair, DP, i,j);
            }
        }

        int minVal = Arrays.stream(DP[p]).min().getAsInt();
        res.append(minVal == Integer.MAX_VALUE ? -1 : minVal);
    }

    private static void updateDP(int[][] repair, int[][] DP, int running, int state) {
        for (int i = 0; i < repair.length; i++) {
            for (int j = 0; j < repair.length; j++) {
                if (i != j && ((state >> i) & 1) == 1 && ((state >> j) & 1) == 0) {
                    DP[running + 1][state | (1 << j)] = Math.min(DP[running][state] + repair[i][j], DP[running + 1][state | (1 << j)]);
                }
            }
        }
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}