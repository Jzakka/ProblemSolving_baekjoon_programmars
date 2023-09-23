import java.io.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MAX_VALUE = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[] students = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(n, students);

        printRes();
    }

    private static void solution(int n, int[] students) {
        int[][] DP = new int[n + 1][n + 1];
        Arrays.stream(DP).forEach(row -> Arrays.fill(row, -1));
        int ans = f(DP, students, 0, n);
        res.append(ans);
    }

    private static int f(int[][] DP, int[] students, int start, int end) {
        if (end == start) {
            return 0;
        }
        if (DP[start][end] != -1) {
            return DP[start][end];
        }

        int ans = Integer.MIN_VALUE;

        for (int i = start; i < end; i++) {
            ans = Math.max(ans, atomic(students, start,i+1) + f(DP, students, i+1, end));
        }

        DP[start][end] = ans;
        return ans;
    }

    private static int atomic(int[] students, int start, int end) {
        int maxStudent = IntStream.range(start, end).map(i -> students[i]).max().getAsInt();
        int minStudent = IntStream.range(start, end).map(i -> students[i]).min().getAsInt();

        return maxStudent - minStudent;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}