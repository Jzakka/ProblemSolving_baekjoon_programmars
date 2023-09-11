import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] seq = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt)
                .toArray();

        solutions(seq);

        printRes();
    }

    private static void solutions(int[] seq) {
        if (Arrays.stream(seq).filter(num -> num >= 0).count() == 0) {
            res.append(Arrays.stream(seq).max().getAsInt());
            return;
        }

        int n = seq.length;
        int[] accSum = new int[n + 2];
        int[] revSum = new int[n + 2];
        int[] leftBoundary = new int[n + 2];
        int[] rightBoundary = new int[n + 2];

        rightBoundary[rightBoundary.length - 1] = rightBoundary.length - 1;


        int minSum = 0;
        int lb = 0;
        for (int i = 1; i <= n; i++) {
            leftBoundary[i] = lb;
            accSum[i] = accSum[i-1] + seq[i - 1];

            if (minSum > accSum[i]) {
                lb = i;
                minSum = accSum[i];
            }
        }

        minSum = 0;
        int rb = n+1;
        for (int i = n; i >= 1 ; i--) {
            rightBoundary[i] = rb;
            revSum[i] = revSum[i+1] + seq[i - 1];

            if (minSum > revSum[i]) {
                rb = i;
                minSum = revSum[i];
            }
        }

        int ans = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            int num = seq[i - 1];

            int subSum = accSum[i] - accSum[leftBoundary[i]] + revSum[i] - revSum[rightBoundary[i]] - num;
            if (num < 0) {
                subSum -= num;
            }

            ans = Math.max(ans, subSum);
        }

        res.append(ans);
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}