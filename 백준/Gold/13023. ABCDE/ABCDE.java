import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int n = info[0];
        int m = info[1];
        int[][] edges = new int[m][];
        for (int i = 0; i < m; i++) {
            edges[i] = getInts();
        }
        solution(n, edges);

        printRes();
    }

    private static void solution(int n, int[][] edges) {
        List<Integer>[] graph = IntStream.range(0, n)
                .mapToObj(i -> new ArrayList())
                .toArray(List[]::new);
        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            graph[src].add(dest);
            graph[dest].add(src);
        }

        for (int i = 0; i < n; i++) {
            for (Integer adj : graph[i]) {
                boolean[] visited = new boolean[n];
                visited[i] = true;
                if (dfs(graph, visited, adj, 0)) {
                    res.append(1);
                    return;
                }
            }
        }

        res.append(0);
    }

    private static boolean dfs(List<Integer>[] graph, boolean[] visited, int cur, int len) {
        if (++len >= 4) {
            return true;
        }
        visited[cur] = true;
        for (Integer adj : graph[cur]) {
            if (visited[adj]) {
                continue;
            }
            if (dfs(graph, visited, adj, len)) {
                return true;
            }
        }
        visited[cur] = false;
        return false;
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
}