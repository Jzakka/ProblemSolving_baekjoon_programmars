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

        int N = Integer.parseInt(inputs[0]);
        int M = Integer.parseInt(inputs[1]);

        int[] A = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] m = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(A, m, M);

        printRes();
    }

    private static void solution(int[] memories, int[] costs, int memory) {
        int[][] DP = new int[memories.length + 1][Arrays.stream(costs).sum() + 1];

        for (int i = 1; i < DP.length; i++) {
            int curMem = memories[i - 1];
            int curCost = costs[i - 1];
            for (int j = 0; j < DP[0].length; j++) {
                if (j - curCost < 0) {
                    DP[i][j] = DP[i - 1][j];
                } else {
                    DP[i][j] = Math.max(DP[i - 1][j], DP[i - 1][j - curCost] + curMem);
                }
            }
        }

        for (int i = 0; i < DP[0].length; i++) {
            if (DP[DP.length - 1][i] >= memory) {
                res.append(i);
                return;
            }
        }
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}