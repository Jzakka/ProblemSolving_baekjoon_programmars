import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        long[] initHeights = getLongs();
        long[] growSpeeds = getLongs();

        solution(initHeights, growSpeeds);

        printRes();
    }

    private static void solution(long[] initHeights, long[] growSpeeds) {
        Arrays.sort(growSpeeds);

        BigInteger ans = new BigInteger("0");
        for (int i = 0; i < growSpeeds.length; i++) {
            ans = ans.add(new BigInteger(String.valueOf(growSpeeds[i])).multiply(new BigInteger(String.valueOf(i))));
        }
        ans = ans.add(new BigInteger(String.valueOf(Arrays.stream(initHeights).sum())));

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