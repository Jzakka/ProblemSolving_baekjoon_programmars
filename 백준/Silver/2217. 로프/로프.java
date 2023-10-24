import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        long[] ropes = new long[n];
        for (int i = 0; i < n; i++) {
            ropes[i] = Long.parseLong(br.readLine());
        }

        solution(ropes);

        printRes();
    }

    private static void solution(long[] ropes) {
        int n = ropes.length;
        Arrays.sort(ropes);

        long ans = 0;
        int k = 1;
        for (int i = n - 1; i >= 0; i--, k++) {
            ans = Math.max(ans, ropes[i] * k);
        }

        res.append(ans);
    }

    /*
    weight 15
    ropes 1 5 6 9

    k개 골랐을 때 w/k가 k개의 로프 한계보다 작거나 같은가? -> 최소인 놈만
     */

    private static int[] getInts() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static long[] getLongs() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+"))
                .mapToLong(Long::parseLong)
                .toArray();
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}