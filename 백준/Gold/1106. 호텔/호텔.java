import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        int[] firstLine = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int c = firstLine[0];
        int n = firstLine[1];

        int[][] advertises = new int[n][];

        for (int i = 0; i < n; i++) {
            advertises[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(c, advertises);

        printRes();
    }

    private static void solution(int c, int[][] advertises) {
        int[] DP = new int[c + 1];
        Arrays.fill(DP, Integer.MAX_VALUE);
        DP[0] = 0;

        for (int[] advertise : advertises) {
            int cost = advertise[0];
            int customers = advertise[1];

            for (int i = 1; i < DP.length; i++) {
                if (customers > i) {
                    DP[i] = Math.min(DP[i], cost);
                } else {
                    DP[i] = Math.min(DP[i], DP[i - customers] + cost);
                }
            }
        }

        res.append(DP[c]);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}