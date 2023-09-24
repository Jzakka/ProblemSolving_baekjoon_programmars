import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[] seq = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(seq);

        printRes();
    }

    private static void solution(int[] seq) {
        int[][] DP = new int[seq.length + 1][seq.length + 1];

        for (int i = 1; i < DP.length; i++) {
            for (int j = 1; j <DP.length; j++) {
                if (seq[seq.length - i] == seq[j - 1]) {
                    DP[i][j] = DP[i - 1][j - 1] + 1;
                } else {
                    DP[i][j] = Math.max(DP[i][j - 1], DP[i - 1][j]);
                }
            }
        }

        res.append(seq.length - DP[DP.length - 1][DP.length - 1]);
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}