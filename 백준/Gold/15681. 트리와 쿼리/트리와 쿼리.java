import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[][] edges = new int[info[0] - 1][];
        int root = info[1];
        int[] queries = new int[info[2]];

        for (int i = 0; i < edges.length; i++) {
            edges[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for (int i = 0; i < queries.length; i++) {
            queries[i] = Integer.parseInt(br.readLine());
        }

        solution(edges, root, queries);

        printRes();
    }

    private static void solution(int[][] edges, int root, int[] queries) {
        List<Integer>[] tree = new List[edges.length + 2];
        IntStream.range(0, tree.length).forEach(i -> tree[i] = new ArrayList<>());

        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];

            tree[src].add(dest);
            tree[dest].add(src);
        }
        int[] DP = new int[edges.length + 2];
        Set<Integer> visited = new HashSet<>();

        traversal(DP, tree, visited, root);

        for (int query : queries) {
            res.append(DP[query]).append("\n");
        }
    }

    private static int traversal(int[] dp, List<Integer>[] tree, Set<Integer> visited, int node) {
        visited.add(node);

        dp[node] = 1;
        for (Integer adjacent : tree[node]) {
            if (!visited.contains(adjacent)) {
                dp[node] += traversal(dp, tree, visited, adjacent);
            }
        }

        return dp[node];
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}