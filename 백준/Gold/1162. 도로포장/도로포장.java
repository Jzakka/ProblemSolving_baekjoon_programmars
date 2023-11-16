import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[][] roads = new int[info[1]][];
        for (int i = 0; i < info[1]; i++) {
            roads[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(info[0], roads, info[2]);

        printRes();
    }

    private static void solution(int n, int[][] roads, int k) {
        long[][] DP = new long[n + 1][k + 1];
        Arrays.stream(DP).forEach(row -> Arrays.fill(row, Long.MAX_VALUE));

        List<int[]>[] graph = new List[n + 1];
        IntStream.range(1, graph.length).forEach(i -> graph[i] = new ArrayList<>());
        for (int[] road : roads) {
            int src = road[0];
            int dest = road[1];
            int cost = road[2];

            graph[src].add(new int[]{dest, cost});
            graph[dest].add(new int[]{src, cost});
        }

        PriorityQueue<long[]> PQ = new PriorityQueue<>(Comparator.comparingLong(arr -> arr[1]));
        // [목적지, 가중치, 포장도로수]
        PQ.add(new long[]{1, 0, 0});
        DP[1][0] = 0;

        while (!PQ.isEmpty()) {
            long[] state = PQ.poll();

            int currentCity = (int) state[0];
            long weight = state[1];
            int asphaltCount = (int) state[2];

            if(DP[currentCity][asphaltCount] < weight){
                continue;
            }

            for (int[] next : graph[currentCity]) {
                int nextCity = next[0];
                int cost = next[1];
                if (weight + cost < DP[nextCity][asphaltCount]) {
                    DP[nextCity][asphaltCount] = weight + cost;
                    PQ.add(new long[]{nextCity, weight + cost, asphaltCount});
                }
                if (asphaltCount + 1 <= k && weight < DP[nextCity][asphaltCount + 1]) {
                    DP[nextCity][asphaltCount + 1] = weight;
                    PQ.add(new long[]{nextCity, weight, asphaltCount + 1});
                }
            }
        }

        res.append(Arrays.stream(DP[n]).min().getAsLong());
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}