import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());


        int[] trees = getInts();
        solution(trees);

        printRes();
    }

    private static void solution(int[] trees) {
        int sum = Arrays.stream(trees).sum();

        int cnt = sum / 3;
        int maxTwoCnt = Arrays.stream(trees).map(i -> i / 2).sum();

        if (cnt * 3 < sum) {
            res.append("NO");
        } else {
            if (cnt <= maxTwoCnt) {
                res.append("YES");
            } else {
                res.append("NO");
            }
        }
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

/*
6
1 1 1 3 3 3
 */