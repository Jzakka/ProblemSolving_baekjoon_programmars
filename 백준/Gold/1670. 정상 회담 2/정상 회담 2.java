import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final long MOD = 987654321L;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        solution(n);

        printRes();
    }

    private static void solution(int n) {
        long[] DP = new long[n + 1];
        DP[0] = 1;

        for (int i = 2; i <= n; i+=2) {
            for (int j = 0; j < i; j+=2) {
                DP[i] += (DP[j] * DP[i - 2 - j])%MOD;
                DP[i] %= MOD;
            }
        }

        res.append(DP[n]);
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
5 3
8 7 4 5 3
2 4 9
 */