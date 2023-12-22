import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] A = getInts();

        solution(A);

        printRes();
    }

    private static void solution(int[] A) {
        int cnt = 0;
        while (notAllZero(A)) {
            for (int i = 0; i < A.length; i++) {
                if ((A[i] & 1) == 1) {
                    cnt++;
                    A[i]--;
                }
            }
            boolean half = false;
            for (int i = 0; i < A.length; i++) {
                if (A[i] > 0 && (A[i] & 1) == 0) {
                    half = true;
                    A[i] >>= 1;
                }
            }
            if (half) {
                cnt++;
            }
        }
        res.append(cnt);
    }

    private static boolean notAllZero(int[] A) {
        for (int a : A) {
            if (a > 0) {
                return true;
            }
        }
        return false;
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

/**
50
1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000 1000
 */