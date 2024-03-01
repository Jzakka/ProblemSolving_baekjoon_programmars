import java.io.*;
import java.util.*;
import java.util.function.Function;
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
        int[] indegree = new int[n + 1];
        Set<Integer>[] graph = new Set[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new HashSet<>();
        }

        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            indegree[dest]++;
            graph[src].add(dest);
        }

        // {num, indegree}
        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.<int[]>comparingInt(arr -> arr[1])
                .thenComparingInt(arr -> arr[0]));

        IntStream.rangeClosed(1,n).forEach(i->PQ.add(new int[]{i, indegree[i]}));

        boolean[] visited = new boolean[n + 1];
        while (!PQ.isEmpty()) {
            int[] poll = PQ.poll();
            int num = poll[0];
            if (visited[num]) {
                continue;
            }
            visited[num] = true;
            res.append(num).append(" ");

            for (Integer adjacent : graph[num]) {
                if (!visited[adjacent]) {
                    indegree[adjacent]--;
                    PQ.add(new int[]{adjacent, indegree[adjacent]});
                }
            }
        }
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

/*
6 4
6 5
5 1
2 1
3 1
 */