import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] priceTable = new int[n][];
        for (int i = 0; i < n; i++) {
            priceTable[i] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(priceTable);

        printRes();
    }

    private static void solution(int[][] priceTable) {
        int n = priceTable.length;

        // DP[i][j][bits] : 마지막 소유자가 i이고 그림가격이 j이고 소유 이력이 bits일 때, 소유자수
        int[][][] DP = new int[n][10][1 << n];

        market(priceTable, DP, 0, 0, 1, 1);

        int ans = 0;
        for (int i = 0; i < DP.length; i++) {
            for (int j = 0; j < DP[0].length; j++) {
                for (int k = 0; k < DP[0][0].length; k++) {
                    ans = Math.max(DP[i][j][k], ans);
                }
            }
        }

        res.append(ans);
    }

    private static void market(int[][] priceTable, int[][][] DP, int price, int seller, int history, int owners) {
        if (DP[seller][price][history] != 0) {
            return;
        }

        DP[seller][price][history] = owners;

        for (int i = 0; i < DP.length; i++) {
            // 현재 셀러가 i번한테 팔 수 있으면 재귀적 판매
            if (((history >> i) & 1) == 0 && price <= priceTable[seller][i]) {
                market(priceTable, DP, priceTable[seller][i], i, (history | (1 << i)), owners + 1);
            }
        }
    }


    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}