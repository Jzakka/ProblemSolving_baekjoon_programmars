import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_003;

    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] superiors = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] good = new int[info[1]][];
        for (int i = 0; i < info[1]; i++) {
            good[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(superiors, good);

        printRes();
    }

    private static void solution(int[] superiors, int[][] good) {
        List<Integer>[] graph = new List[superiors.length + 1];
        IntStream.range(1, graph.length).forEach(i -> graph[i] = new ArrayList<>());

        for (int i = 1; i < superiors.length; i++) {
            int superior = superiors[i];
            int subordinate = i + 1;
            graph[superior].add(subordinate);
        }

        int[] DP = new int[graph.length];
        for (int[] goodInfo : good) {
            int who = goodInfo[0];
            int how = goodInfo[1];

            DP[who] += how;
        }

        boolean[] visited = new boolean[graph.length];
        dfs(DP, graph, visited, 1, 0);

        IntStream.range(1, DP.length).forEach(i -> res.append(DP[i]).append(" "));
    }

    private static void dfs(int[] DP, List<Integer>[] graph, boolean[] visited, int employee, int good) {
        visited[employee] = true;
        DP[employee] += good;

        for (Integer subordinate : graph[employee]) {
            if (!visited[subordinate]) {
                dfs(DP, graph, visited, subordinate, DP[employee]);
            }
        }
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

// 0100010