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
        int m = Integer.parseInt(br.readLine());

        int[][] parts = new int[m][3];
        for (int i = 0; i < m; i++) {
            parts[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(n, parts);

        printRes();
    }

    private static void solution(int n, int[][] parts) {
        int[] DP = new int[n + 1];
        DP[n] = 1;
        int[] visited = new int[n + 1];

        Set<Integer> notPrimitives = new HashSet<>();
        List<int[]>[] graph = new List[n + 1];
        IntStream.rangeClosed(1, n).forEach(i -> graph[i] = new ArrayList<>());
        for (int[] part : parts) {
            int src = part[0];
            int dest = part[1];
            int count = part[2];

            graph[src].add(new int[]{dest, count});
            visited[dest]++;
            notPrimitives.add(src);
        }

        int[] primitives = IntStream.rangeClosed(1, n)
                .filter(i -> !notPrimitives.contains(i))
                .sorted()
                .toArray();


        DP[n] = 1;
        dfs(DP, visited, graph, n);

        for (int primitive : primitives) {
            res.append(primitive).append(" ").append(DP[primitive]).append("\n");
        }
    }

    private static void dfs(int[] DP, int[] visited, List<int[]>[] graph, int node) {
        for (int[] prePart : graph[node]) {
            int preNode = prePart[0];
            int preCount = prePart[1];

            if (visited[preNode] > 0) {
                visited[preNode]--;
                DP[preNode] += DP[node] * preCount;
            }

            if (visited[preNode] == 0) {
                dfs(DP, visited, graph, preNode);
            }
        }
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}