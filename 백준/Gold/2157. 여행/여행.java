import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = info[0];
        int m = info[1];
        int k = info[2];

        int[][] edges = new int[k][];
        for (int i = 0; i < k; i++) {
            edges[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(n, m, edges);

        printRes();
    }

    private static void solution(int n, int m, int[][] edges) {
        // DP[i][j] : i번 도시를 j번째로 방문할 때 최대 기내식 점수
        int[][] DP = new int[n + 1][m];

        List<int[]>[] graph = new List[n + 1];
        IntStream.rangeClosed(1, n).forEach(i -> graph[i] = new ArrayList<>());

        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            int cost = edge[2];

            if (src < dest) {
                graph[src].add(new int[]{dest, cost});
            }
        }

        for (int[] edge : graph[1]) {
            int nextCity = edge[0];
            int cost = edge[1];

            DP[nextCity][1] = Math.max(DP[nextCity][1], DP[1][0] + cost);
        }

        for (int i = 2; i < n; i++) {
            for (int j = 0; j < m-1; j++) {
                if (DP[i][j] > 0) {
                    for (int[] edge : graph[i]) {
                        int nextCity = edge[0];
                        int cost = edge[1];

                        DP[nextCity][j + 1] = Math.max(DP[nextCity][j + 1], DP[i][j] + cost);
                    }
                }
            }
        }

        res.append(Arrays.stream(DP[n]).max().getAsInt());
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}