import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        br.readLine();
        int[] boys = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int[] girls = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        solution(boys, girls);

        printRes();
    }

    private static void solution(int[] boys, int[] girls) {
        if (boys.length < girls.length) {
            int[] temp = girls;
            girls = boys;
            boys = temp;
        }

        int n = boys.length;
        int m = girls.length;
        Arrays.sort(boys);
        Arrays.sort(girls);
        // DP[i][j] : 남자가 i번까지 있고 j번 여자들이 모두 매칭됐을 때 최소 성격차이 합
        long[][] DP = new long[n][m];

        Arrays.stream(DP).forEach(row -> Arrays.fill(row, Long.MAX_VALUE/2));

        DP[0][0] = Math.abs(boys[0] - girls[0]);

        for (int i = 1; i < n; i++) {
            DP[i][0] = Math.min(DP[i - 1][0], Math.abs(boys[i] - girls[0]));
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                DP[i][j] = Math.min(DP[i - 1][j - 1] + Math.abs(boys[i] - girls[j]), DP[i - 1][j]);
            }
        }

        res.append(DP[n - 1][m - 1]);
    }


    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
5 3
8 7 4 5 3
2 4 9
 */