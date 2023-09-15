import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[] village = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[][] edges = new int[n - 1][];
        for (int i = 0; i < edges.length; i++) {
            edges[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(village, edges);

        printRes();
    }

    private static void solution(int[] village, int[][] edges) {
        int[][] DP = new int[village.length+1][2];

        List<Integer>[] graph = new List[village.length + 1];
        boolean[] visited = new boolean[village.length + 1];
        IntStream.range(1, graph.length).forEach(i -> graph[i] = new ArrayList<>());

        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];

            graph[src].add(dest);
            graph[dest].add(src);
        }

        dfs(DP, visited, village, graph, 1);

        res.append(Math.max(DP[1][0], DP[1][1]));
    }

    // node번 마을의 주민수 = village[node-1]
    // 전략 1. 리프노드를 우수 마을로 넣기
    // 전략 2. 리프노드를 우수 마을에서 빼기
    private static void dfs(int[][] DP, boolean[] visited, int[] village, List<Integer>[] graph, int node) {
        visited[node] = true;
        for (Integer neighbor : graph[node]) {
            if (!visited[neighbor]) {
                dfs(DP, visited, village, graph, neighbor);
                DP[node][1] += Math.max(DP[neighbor][0], DP[neighbor][1]);
                DP[node][0] += DP[neighbor][1];
            }
        }
        DP[node][0] += village[node - 1];
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}