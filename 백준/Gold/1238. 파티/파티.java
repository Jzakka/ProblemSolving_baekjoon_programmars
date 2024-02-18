import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int n = info[0];
        int m = info[1];
        int x = info[2];

        long[][] adjMat = new long[n + 1][n + 1];
        Arrays.stream(adjMat).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));
        IntStream.rangeClosed(0,n).forEach(i -> adjMat[i][i] = 0);
        for (int i = 0; i < m; i++) {
            int[] edge = getInts();
            int src = edge[0];
            int dest = edge[1];
            int cost = edge[2];

            adjMat[src][dest] = cost;
        }

        solution(n, adjMat, x);

        printRes();
    }

    private static void solution(int n, long[][] adjMat, int x) {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (adjMat[j][i] != Integer.MAX_VALUE) {
                    for (int k = 1; k <= n; k++) {
                        adjMat[j][k] = Math.min(adjMat[j][k], adjMat[j][i] + adjMat[i][k]);
                    }
                }
            }
        }
        long ans = IntStream.rangeClosed(1, n)
                .mapToLong(i -> adjMat[i][x] + adjMat[x][i])
                .max()
                .orElse(0);

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

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }

    private static class SumAndCount {
        int sum = 0;

        List<Integer> nodes = new ArrayList<>();

        int getAvg() {
            return sum / nodes.size();
        }
    }

    private static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}