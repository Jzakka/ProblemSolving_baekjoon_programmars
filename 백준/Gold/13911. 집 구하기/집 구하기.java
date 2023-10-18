import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] size = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int v = size[0];
        int e = size[1];

        int[][] roads = new int[e][];

        for (int i = 0; i < e; i++) {
            roads[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        int[] macInfo = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int x = macInfo[1];
        int[] macdonalds = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] starbucksInfo = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int y = starbucksInfo[1];
        int[] starbucks = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(v, roads, x, macdonalds, y, starbucks);

        printRes();
    }

    private static void solution(int v, int[][] roads, int x, int[] macdonalds, int y, int[] starbucks) {
        List<int[]>[] edges = new List[v + 1];
        IntStream.rangeClosed(1, v).forEach(i -> edges[i] = new ArrayList<>());

        for (int[] road : roads) {
            int src = road[0];
            int dest = road[1];
            int cost = road[2];

            edges[src].add(new int[]{dest, cost});
            edges[dest].add(new int[]{src, cost});
        }

        long[] macDist = new long[v + 1];
        Arrays.fill(macDist, Long.MAX_VALUE/3);
        calcDist(macdonalds, macDist, edges);

        long[] sbDist = new long[v + 1];
        Arrays.fill(sbDist, Long.MAX_VALUE/3);
        calcDist(starbucks, sbDist, edges);

        Set<Integer> notHouses = new HashSet<>();
        Arrays.stream(macdonalds).forEach(notHouses::add);
        Arrays.stream(starbucks).forEach(notHouses::add);

        long ans = Long.MAX_VALUE;
        for (int i = 1; i <= v; i++) {
            if (!notHouses.contains(i) && macDist[i] <= x && sbDist[i] <= y) {
                ans = Math.min(ans, macDist[i] + sbDist[i]);
            }
        }

        res.append((ans < Long.MAX_VALUE / 3 + Long.MAX_VALUE / 3) ? ans : -1);
    }

    private static void calcDist(int[] sources, long[] distances, List<int[]>[] edges) {
        int v = edges.length - 1;
        boolean[] visited = new boolean[v + 1];
        PriorityQueue<Integer> PQ = new PriorityQueue<>(Comparator.comparingLong(node -> distances[node]));

        for (int src : sources) {
            PQ.add(src);
            distances[src] = 0;
        }

        while (!PQ.isEmpty()) {
            Integer node = PQ.poll();
            if (visited[node]) {
                continue;
            }

            visited[node] = true;

            for (int[] incident : edges[node]) {
                int incidentNode = incident[0];
                int dist = incident[1];

                if (!visited[incidentNode] && distances[node] + dist < distances[incidentNode]) {
                    distances[incidentNode] = distances[node] + dist;
                    PQ.add(incidentNode);
                }
            }
        }
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
6 6
1 2 4
1 4 3
3 4 7
3 2 9
3 5 1
3 6 1
2 3
1 5
2 3
1 6
 */
