import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = getInts();

        long[] blobs = getLongs();

        solution(info[1], blobs);

        printRes();
    }

    private static void solution(int k, long[] blobs) {
        long[] modBlobs = Arrays.stream(blobs).map(i -> i % k).toArray();

        if (Arrays.stream(blobs).sum() % k != 0) {
            res.append("blobsad");
            return;
        }

        long ans = 0;
        long accSum = 0;
        for (int i = 0; i < modBlobs.length - 1; i++) {
            accSum += modBlobs[i];
            accSum %= k;

            ans += Math.min(accSum, k - accSum);
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
/*
4 11
100000000 1000000000 100000000 1000000000
 */
