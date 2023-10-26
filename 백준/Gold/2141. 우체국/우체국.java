import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        long[][] villages = new long[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            villages[i][0] = Integer.parseInt(st.nextToken());
            villages[i][1] = Integer.parseInt(st.nextToken());
        }

        solution(villages);

        printRes();
    }

    private static void solution(long[][] villages) {
        Arrays.sort(villages, Comparator.comparingLong(village -> village[0]));
        BigInteger half = Arrays.stream(villages).map(village -> new BigInteger(String.valueOf(village[1])))
                .reduce(new BigInteger("0"), (acc, cur) -> acc = acc.add(cur))
                .add(new BigInteger("1"))
                .divide(new BigInteger("2"));

        BigInteger acc = new BigInteger("0");
        long ans = 1;

        for (long[] village : villages) {
            ans = village[0];
            acc = acc.add(new BigInteger(String.valueOf(village[1])));
            if (acc.compareTo(half) >= 0) {
                break;
            }
        }

        res.append(ans);
    }

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