import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int v = info[0];
        int e = info[1];
        int k = Integer.parseInt(br.readLine());

        List<int[]>[] graph = new List[v + 1];
        IntStream.rangeClosed(1, v).forEach(i -> graph[i] = new ArrayList<>());

        for (int i = 0; i < e; i++) {
            int[] edge = getInts();
            int src = edge[0];
            int dest = edge[1];
            int weight = edge[2];

            graph[src].add(new int[]{dest, weight});
        }

        solution(graph, k);

        printRes();
    }

    private static void solution(List<int[]>[] graph, int k) {
        int n = graph.length - 1;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        dist[k] = 0;
        pq.add(new int[]{k, 0});

        while (!pq.isEmpty()){
            int[] poped = pq.poll();

            int nodeNum = poped[0];
            int minDist = poped[1];

            if(dist[nodeNum] < minDist){
                continue;
            }

            for (int[] adjacents : graph[nodeNum]) {
                int incident = adjacents[0];
                int weight = adjacents[1];

                if (dist[incident] > dist[nodeNum] + weight) {
                    dist[incident] = dist[nodeNum] + weight;
                    pq.add(new int[]{incident, dist[incident]});
                }
            }
        }

        IntStream.rangeClosed(1, n)
                .forEach(i -> {
                    if (dist[i] == Integer.MAX_VALUE) {
                        res.append("INF");
                    } else {
                        res.append(dist[i]);
                    }
                    res.append("\n");
                });
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

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}