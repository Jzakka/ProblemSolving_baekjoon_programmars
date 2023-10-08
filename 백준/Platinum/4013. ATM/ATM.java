import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] nm = getIntArrayFromLine();

        int[][] roads = new int[nm[1]][];
        for (int i = 0; i < nm[1]; i++) {
            roads[i] = getIntArrayFromLine();
        }

        int[] atm = new int[nm[0] + 1];
        for (int i = 1; i <= nm[0]; i++) {
            atm[i] = Integer.parseInt(br.readLine());
        }

        int[] sp = getIntArrayFromLine();
        int[] restaurants = getIntArrayFromLine();

        solution(nm[0], roads, atm, sp[0], restaurants);

        printRes();
    }

    private static int[] getIntArrayFromLine() throws IOException {
        return Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
    }

    private static void solution(int n, int[][] roads, int[] atm, int s, int[] restaurants) {
        List<Integer>[] graph = new List[n + 1];
        IntStream.rangeClosed(1, n).forEach(i -> graph[i] = new ArrayList<>());
        for (int[] road : roads) {
            int src = road[0];
            int dest = road[1];
            graph[src].add(dest);
        }

        int[] scc = scc(graph);

        List<Integer>[] sccGraph = new List[n + 1];
        IntStream.rangeClosed(1, n).forEach(i -> sccGraph[i] = new ArrayList<>());

        int[] sccIndgree = new int[scc.length];
        // scc간의 그래프로 변경
        for (int i = 1; i <= n; i++) {
            int srcScc = scc[i];

            for (Integer next : graph[i]) {
                int destScc = scc[next];
                if (srcScc != destScc) {
                    sccGraph[srcScc].add(destScc);
                    sccIndgree[destScc]++;
                }
            }
        }

        // i번 scc가 가진 자산
        int[] sccMoneys = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sccMoneys[scc[i]] += atm[i];
        }

        // scc그래프를 기준으로 bfs탐색
        // DP[i] : scc[s]에서 scc[i]까지 이동했을 떄 최대 현금
        int[] DP = new int[n + 1];

        Queue<Integer> Q = new LinkedList<>();
        DP[scc[s]] = sccMoneys[scc[s]];
        Q.add(scc[s]);

        while (!Q.isEmpty()) {
            int qLen = Q.size();
            for (int i = 0; i < qLen; i++) {
                Integer current = Q.poll();

                for (Integer next : sccGraph[current]) {
                    if (DP[next] < DP[current] + sccMoneys[next]) {
                        DP[next] = DP[current] + sccMoneys[next];
                        Q.add(next);
                    }
                }
            }
        }


        int ans = 0;
        for (int restaurant : restaurants) {
            int componentId = scc[restaurant];
            ans = Math.max(ans, DP[componentId]);
        }

        res.append(ans);
    }

    private static final int[] scc(List<Integer>[] graph) {
        // 역방향 그래프 생성
        List<Integer>[] reverse = reverse(graph);

        // 방문종료 스택 생성
        Stack<Integer> stk = new Stack<>();
        boolean[] visited = new boolean[graph.length];
        for (int i = 1; i < graph.length; i++) {
            if (!visited[i]) {
                dfs(stk, graph, visited, i);
            }
        }

        int[] scc = new int[graph.length];
        while (!stk.isEmpty()) {
            Integer node = stk.pop();

            if (scc[node] == 0) {
                dfs(scc, node, reverse, node);
            }
        }

        return scc;
    }

    private static void dfs(int[] scc, final int leader, List<Integer>[] graph, Integer current) {
        scc[current] = leader;

        for (Integer next : graph[current]) {
            if (scc[next] == 0) {
                dfs(scc, leader, graph, next);
            }
        }
    }

    private static void dfs(Stack<Integer> stk, List<Integer>[] graph, boolean[] visited, int current) {
        visited[current] = true;

        for (Integer next : graph[current]) {
            if (!visited[next]) {
                dfs(stk, graph, visited, next);
            }
        }

        stk.add(current);
    }

    private static List<Integer>[] reverse(List<Integer>[] graph) {
        List<Integer>[] reversed = new List[graph.length];
        IntStream.range(1, reversed.length).forEach(i -> reversed[i] = new ArrayList<>());

        for (int src = 1; src < graph.length; src++) {
            for (Integer dest : graph[src]) {
                reversed[dest].add(src);
            }
        }

        return reversed;
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}