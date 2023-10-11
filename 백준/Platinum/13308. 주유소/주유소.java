import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] nm = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int n = nm[0];
        int m = nm[1];

        int[] stations = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[][] roads = new int[m][];

        for (int i = 0; i < m; i++) {
            roads[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(stations, roads);

        printRes();
    }

    private static void solution(int[] stations, int[][] roads) {
        int n = stations.length;
        int m = roads.length;

        int[] temp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            temp[i + 1] = stations[i];
        }
        stations = temp;

        int[] DP = new int[n + 1];

        List<int[]>[] graph = new List[n + 1];
        IntStream.range(1, graph.length).forEach(i -> graph[i] = new ArrayList<>());

        for (int[] road : roads) {
            int src = road[0];
            int dest = road[1];
            int cost = road[2];

            graph[src].add(new int[]{dest, cost});
            graph[dest].add(new int[]{src, cost});
        }

        int[][] allPairShortestDistance = new int[n + 1][];

        for (int i = 1; i <= n; i++) {
            allPairShortestDistance[i] = dijkstra(graph, i);
        }

        List<int[]>[] allPairGraph = new List[n + 1];
        IntStream.range(1, allPairGraph.length).forEach(i -> allPairGraph[i] = new ArrayList<>());

        for (int i = 1; i < allPairShortestDistance.length; i++) {
            for (int j = 1; j < allPairShortestDistance[i].length; j++) {
                allPairGraph[i].add(new int[]{j, allPairShortestDistance[i][j] * stations[i]});
            }
        }

        int[] ans = dijkstra(allPairGraph, 1);
        res.append(ans[n]);
    }


    private static int dfs(int[] stations, List<int[]>[] graph, boolean[] visited, int current, int acc, int literPerKm) {
        if (current == graph.length - 1) {
            return acc;
        }

        visited[current] = true;

        int ans = Integer.MAX_VALUE;
        for (int[] road : graph[current]) {
            int next = road[0];
            int cost = road[1];

            if (!visited[next]) {
                if (literPerKm > stations[next]) {
                    ans = Math.min(ans, dfs(stations, graph, visited, next, acc + literPerKm * cost, stations[next]));
                } else {
                    ans = Math.min(ans, dfs(stations, graph, visited, next, acc + literPerKm * cost, literPerKm));
                }
            }
        }
        visited[current] = false;

        return ans;
    }


    private static int[] dijkstra(List<int[]>[] graph, int start) {
        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        PriorityQueue<Integer> PQ = new PriorityQueue<>(Comparator.comparingInt(city -> distances[city]));
        PQ.add(start);
        distances[start] = 0;
        boolean[] visited = new boolean[graph.length];

        while (!PQ.isEmpty()) {
            Integer city = PQ.poll();

            if (visited[city]) {
                continue;
            }
            visited[city] = true;

            for (int[] edge : graph[city]) {
                int nextCity = edge[0];
                int cost = edge[1];

                if (!visited[nextCity] && distances[city] + cost < distances[nextCity]) {
                    distances[nextCity] = distances[city] + cost;
                    PQ.add(nextCity);
                }
            }
        }

        return distances;
    }


    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}