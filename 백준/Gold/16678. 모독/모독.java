import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static int[][] DP = new int[10_001][101];

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] honors = new int[n];
        for (int i = 0; i < n; i++) {
            honors[i] = Integer.parseInt(br.readLine());
        }

        solution(honors);

        printRes();
    }

    private static void solution(int[] honors) {
        Arrays.sort(honors);

        long expect = 1;
        long hackers = 0;
        for (int honor : honors) {
            if (expect <= honor) {
                hackers += honor - expect;
                expect++;
            }
        }

        res.append(hackers);
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