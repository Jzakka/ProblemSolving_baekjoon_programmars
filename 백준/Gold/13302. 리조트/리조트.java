import java.io.*;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MAX_VALUE = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = info[0];
        int m = info[1];
        int[] unholidays;
        if (m == 0) {
            unholidays = new int[]{-1};
        } else {
            unholidays = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(n, unholidays);

        printRes();
    }

    private static void solution(int n, int[] unholidays) {
        Set<Integer> cantRest = Arrays.stream(unholidays).boxed().collect(Collectors.toSet());

        int[] day = {0, 1, 3, 5};
        int[] cost = {0, 10_000, 25_000, 37_000};
        int[] coupon = {0, 0, 1, 2};

        int[][][] DP = new int[n + 1][4][n + 1];
        Arrays.stream(DP).forEach(row -> Arrays.stream(row).forEach(col -> Arrays.fill(col, MAX_VALUE)));

        DP[0][0][0] = 0;

        for (int i = 1; i < DP.length; i++) {
//            System.out.println("DAY#" + i);

            if (cantRest.contains(i)) {
                for (int k = 0; k < DP[0][0].length; k++) {
                    final int coupons = k;
                    DP[i][0][k] = Arrays.stream(DP[i - 1]).mapToInt(row -> row[coupons]).min().getAsInt();
//                    System.out.printf("\t\t\t%d일이 쉴 수 없는 날이고 쿠폰이 %d개 있을때 최소합:%d%n", i, k, DP[i][0][k]);
                }
            }

            for (int j = 1; j < DP[0].length; j++) {
//                System.out.printf("\tTICKET#%d: {day:%d, cost:%d, coupon:%d} %n", j, day[j], cost[j], coupon[j]);
                final int tickets = j;

                for (int k = 0; k < DP[0][0].length; k++) {
                    final int coupons = k;
//                    System.out.printf("\t\tCOUPON COUNTS:%d%n", coupons);

                    if (day[j] == 1) {
                        DP[i][j][k] = Math.min(
                                Arrays.stream(DP[i - day[j]])
                                        .mapToInt(row -> row[coupons])
                                        .min()
                                        .getAsInt() + cost[j],
                                Arrays.stream(DP[i - day[j]])
                                        .mapToInt(row -> coupons + 3 < row.length ? row[coupons + 3] : MAX_VALUE)
                                        .min()
                                        .getAsInt());

//                        System.out.printf("\t\t\t%d일이 %d쿠폰의 마지막 날이고 쿠폰이 %d개 있을때 최소합:%d%n", i, j, k, DP[i][j][k]);
                    } else {
                        if (i - day[j] >= 0) {
                            DP[i][j][k] = Arrays.stream(DP[i - day[j]])
                                    .mapToInt(row -> coupons - coupon[tickets] >= 0 ? row[coupons - coupon[tickets]] : MAX_VALUE)
                                    .min()
                                    .getAsInt() + cost[j];

//                            System.out.printf("\t\t\t%d일이 %d쿠폰의 마지막 날이고 쿠폰이 %d개 있을때 최소합:%d%n", i, j, k, DP[i][j][k]);
                        }
                    }
                }
            }
        }

        res.append(Arrays.stream(DP[n]).flatMapToInt(Arrays::stream).min().getAsInt());
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}