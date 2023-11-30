import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        long[] honey = getLongs();

        solution(honey);

        printRes();
    }

    private static void solution(long[] honey) {
        int n = honey.length;
        long[] subSum1 = new long[n];
        long[] subSum2 = new long[n]; // 역순 부분합

        long subSum = 0;
        for (int i = 0; i < n; i++) {
            subSum += honey[i];
            subSum1[i] = subSum;
        }

        subSum = 0;
        for (int i = n - 1; i >= 0; i--) {
            subSum += honey[i];
            subSum2[i] = subSum;
        }

        long ans = 0;
        for (int i = 1; i < n - 1; i++) {
            long hiveAtRight = subSum1[n - 1] * 2 - honey[0] - honey[i] - subSum1[i];
            long hiveAtMid = subSum1[i] - honey[0] + subSum2[i] - honey[n - 1];
            long hiveAtLeft = subSum2[0] * 2 - honey[i] - honey[n - 1] - subSum2[i];

            ans = Math.max(ans, Math.max(hiveAtLeft, Math.max(hiveAtMid, hiveAtRight)));
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
    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}