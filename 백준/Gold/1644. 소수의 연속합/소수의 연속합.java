import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        boolean[] isPrimitive = new boolean[5_000_001];
        Arrays.fill(isPrimitive, true);
        isPrimitive[0] = isPrimitive[1] = false;

        List<Integer> primitives = new ArrayList<>();

        for (int i = 2; i <= Math.ceil(Math.sqrt(5_000_000)); i++) {
            if (isPrimitive[i]) {
                for (int j = i * i; j < 5_000_001; j += i) {
                    isPrimitive[j] = false;
                }
            }
        }

        IntStream.range(0, 4_000_001).filter(i -> isPrimitive[i]).forEach(primitives::add);

        int l = 0;
        int r = -1;
        int sum = 0;

//        int lastPrimeNumber = -1;
//        for (int i = 2; i < 4_000_000; i++) {
//            if (isPrimitive[i] ^ new BigInteger(String.valueOf(i)).isProbablePrime(10)) {
//                int a  =1;
//            }
//            lastPrimeNumber = i;
//        }
//        System.out.println("lastPrimeNumber = " + lastPrimeNumber);

        int ans = 0;
        while (r < primitives.size()) {
            if (sum <= n) {
                if (sum == n) {
                    ans++;
                }
                r++;
                if (r == primitives.size()) {
                    break;
                }
                sum += primitives.get(r);
            } else if (sum > n) {
                sum -= primitives.get(l);
                l++;
            }

        }
// 2 3 5 7 11 13 17
        res.append(ans);

//        int[] nkm = Arrays.stream(br.readLine().split("\\s+"))
//                .mapToInt(Integer::parseInt)
//                .toArray();
//        int n = nkm[0];
//        int k = nkm[1];
//        int m = nkm[2];
//
//        solution(n, k, m);

        printRes();
    }

    private static void solution(int n, int k, int m) {
        int[][] DP = new int[m + 1][m + 1];

        for (int i = 1; i <= m; i++) {
            DP[i][1] = DP[i][i] = 1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 2; j < i; j++) {
                DP[i][j] = (DP[i - 1][j - 1] + DP[i - 1][j]) % m;
            }
        }

        n %= m;
        k %= m;

        if (n == 0) {
            n += m;
        }
        if (k == 0) {
            k += m;
        }
        res.append(DP[n][k]);
    }

    /*
1           1         m = 5
2          1 1
3         1 2 1
4        1 3 3 1
5       1 4 1 4 1
6      1 0 0 0 0 1
7     1 1 0 0 0 1 1
8    1 2 1 0 0 1 2 1
9   1 3 3 1 0 1 3 3 1
10 1 4 1 4 1 1 4 1 4 1

     */

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}