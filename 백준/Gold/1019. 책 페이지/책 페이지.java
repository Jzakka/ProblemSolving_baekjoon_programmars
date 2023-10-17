import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        solution(n);

        printRes();
    }

    private static void solution(long n) {
        int[] ans = new int[10];
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j < 10; j++) {
                ans[j] += count(n, i, j);
            }
        }

        for (int a : ans) {
            res.append(a).append(" ");
        }
    }

    private static int count(long n, int e, int digit) {
        int ans = 0;

        long q = n / (long)Math.pow(10, e + 1);
        if (q == 0 && digit == 0) {
            return 0;
        }

        ans += q * (long)Math.pow(10, e);

        long at = n % (long)Math.pow(10, e + 1) / (long)Math.pow(10, e);
        if (at > digit) {
            ans += (long)Math.pow(10, e);
        } else if (at < digit) {
            // 더해줄 것 없음
        } else {
            ans += n % Math.pow(10, e) + 1;
        }

        if (digit == 0) {
            ans -= (long) Math.pow(10, e);
        }

        return ans;
    }


    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}
