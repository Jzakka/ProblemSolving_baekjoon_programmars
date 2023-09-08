import java.io.*;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] schedule = new int[n][];

        for (int i = 0; i < n; i++) {
            schedule[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(schedule);

        printRes();
    }

    private static void solution(int[][] schedule) {
        int[] DP = new int[schedule.length + 1];

        int profit = 0;
        for (int i = 0; i < schedule.length; i++) {
            profit = Math.max(DP[i], profit);
            int duration = schedule[i][0];
            int pay = schedule[i][1];

            if (i + duration < DP.length) {
                DP[i + duration] = Math.max(DP[i + duration], profit + pay);
            }
        }

        int ans = Arrays.stream(DP).max().getAsInt();
        res.append(ans);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}