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

        int[] nodes = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] edges = new int[n - 1][];
        for (int i = 0; i < n-1; i++) {
            edges[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(nodes, edges);

        printRes();
    }

    private static void solution(int[] nodes, int[][] edges) {
        int n = nodes.length;

        // DP[i][0] : i번 노드가 독립집합에 포함되지 않을 때, 가중치 합
        // DP[i][1] : i번 노드가 독립집합에 포함될 때, 가중치 합
        int[][] DP = new int[n + 1][2];

        // trace[i][0] : i번 노드가 독립집합에 포함되지 않을 때, 이전 노드
        // trace[i][1] : i번 노드가 독립집합에 포함될 때, 이전 노드
        StringBuilder[][] trace = new StringBuilder[n + 1][2];

        List<Integer>[] graph = new List[n + 1];
        IntStream.rangeClosed(1, n).forEach(i -> graph[i] = new ArrayList<>());
        for (int i = 0; i < edges.length; i++) {
            int src = edges[i][0];
            int dest = edges[i][1];

            graph[src].add(dest);
            graph[dest].add(src);
        }

        boolean[] visited = new boolean[n + 1];

        dfs(DP,trace, visited, nodes, graph, 1);

        int ans = Math.max(DP[1][0], DP[1][1]);
        res.append(ans).append("\n");

        if (ans == DP[1][0]) {
            traceBack(trace[1][0]);
        } else {
            traceBack(trace[1][1]);
        }
    }

    private static void traceBack(StringBuilder trace) {
        TreeSet<Integer> asc = new TreeSet<>();

        Arrays.stream(trace.toString().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .forEach(asc::add);

        asc.forEach(node -> res.append(node).append(" "));
    }

    private static void dfs(int[][] DP, StringBuilder[][] trace, boolean[] visited, int[] nodes, List<Integer>[] graph, Integer node) {
        visited[node] = true;
        DP[node][1] = nodes[node - 1];
        trace[node][0] = new StringBuilder();
        trace[node][1] = new StringBuilder(node.toString()).append(" ");

        for (Integer next : graph[node]) {
            if (!visited[next]) {
                dfs(DP, trace, visited, nodes, graph, next);

                int bigger = Math.max(DP[next][0], DP[next][1]);
                DP[node][0] += bigger;
                if (bigger == DP[next][0]) {
                    trace[node][0].append(trace[next][0]).append(" ");
                } else {
                    trace[node][0].append(trace[next][1]).append(" ");
                }
                DP[node][1] += DP[next][0];
                trace[node][1].append(trace[next][0]).append(" ");
            }
        }
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}