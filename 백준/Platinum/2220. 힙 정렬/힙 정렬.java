import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[] ans = solution(n);

        for (int e : ans) {
            res.append(e).append(" ");
        }

        bw.write(res.toString());
        bw.close();
    }

    private static int[] solution(int n) {
        int[] ans = new int[n];
        ans[0] = 1;

        for (int i = 2; i <= n; i++) {
            heapAdd(ans, i);
        }

        return ans;
    }

    private static void heapAdd(int[] ans, int k) {
        int idx = k - 2;
        while (idx > 0) {
            int parentIdx = (idx - 1) / 2;
            swap(ans, idx, parentIdx);
            idx = parentIdx;
        }

        ans[k - 1] = k;
        swap(ans, 0, k - 1);
    }

    private static void swap(int[] ans, int idx1, int idx2) {
        int tmp = ans[idx1];
        ans[idx1] = ans[idx2];
        ans[idx2] = tmp;
    }

    public static int[] getInts() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }

        return ints;
    }
}