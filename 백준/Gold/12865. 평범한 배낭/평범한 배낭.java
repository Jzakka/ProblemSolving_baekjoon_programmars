import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();


    public static void main(String[] args) throws Exception {
        String[] firstInputs = br.readLine().split("\\s+");
        int n = Integer.parseInt(firstInputs[0]);
        int k = Integer.parseInt(firstInputs[1]);

        int[][] items = new int[n][];
        for (int i = 0; i < n; i++) {
            items[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(items, k);

        printRes();
    }

    private static void solution(int[][] items, int k) {
        int[][] DP = new int[items.length + 1][k + 1]; // DP[i][j] : 물건을 1번부터 i번까지만 담을 수 있고 가방 용량이 j일 때의 최대 이익

        for (int i = 0; i < items.length; i++) {
            int itemNum = i + 1;
            int itemWeight = items[i][0];
            int itemValue = items[i][1];

            for (int weight = 0; weight <= k; weight++) {
                if (weight - itemWeight >= 0) {
                    DP[itemNum][weight] = Math.max(itemValue + DP[itemNum - 1][weight - itemWeight], DP[itemNum - 1][weight]);
                } else {
                    DP[itemNum][weight] = DP[itemNum - 1][weight];
                }
            }
        }

        res.append(DP[items.length][k]);
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}