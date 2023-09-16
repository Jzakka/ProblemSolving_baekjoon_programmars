import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] train = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int lead = Integer.parseInt(br.readLine());

        solution(n, train, lead);

        printRes();
    }

    private static void solution(int n, int[] train, int lead) {
        int[] sum = new int[n + 1];
        for (int i = 0; i < train.length; i++) {
            sum[i+1] += sum[i] + train[i];
        }

        int[][] DP = new int[4][n + 1];

        for (int i = 1; i <= 3; i++) {
            for (int j = lead * (i - 1) + 1; j + lead <= n + 1; j++) {
                if (j - lead < 0) {
                    DP[i][j] = sum[j + lead - 1] - sum[j - 1];
                    continue;
                }
                DP[i][j] = Math.max(DP[i][j-1], DP[i - 1][j - lead] + sum[j + lead - 1] - sum[j - 1]);
            }
        }

        res.append(Arrays.stream(DP[3]).max().getAsInt());
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}