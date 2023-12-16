import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n, m, k;
        int[] nmk = getInts();
        n = nmk[0];
        m = nmk[1];
        k = nmk[2];

        solution(n, m, k);

        printRes();
    }

    private static void solution(int n, int m, int k) {
        if (!(m + k - 1 <= n && n <= m * k)) {
            res.append(-1);
            return;
        }

        int i = 1;
        // 첫번째 LDS 그룹은 무조건 k개여야 함
        // 그리고 크기 가장 작은 그룹임
        reversePrint(i, i + k);
        i += k;

        if (m - 1 == 0) {
            return;
        }
        int q = (n - k) / (m - 1);
        int r = (n - k) % (m - 1);

        int j = 1;
        for (; j <= r; j++) {
            reversePrint(i, i + q + 1);
            i += q + 1;
        }

        for (; j <= (m - 1); j++) {
            reversePrint(i, i + q);
            i += q;
        }
    }

    /*
    end exclusive
     */
    private static void reversePrint(int start, int end) {
        for (int i = end - 1; i >= start; i--) {
            res.append(i).append(" ");
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