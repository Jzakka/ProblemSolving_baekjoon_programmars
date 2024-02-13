import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int[][] edges = new int[m][];
        for (int i = 0; i < m; i++) {
            edges[i] = getInts();
        }
        int[] srcdest = getInts();

        solution(n, edges, srcdest[0], srcdest[1]);

        printRes();
    }

    private static void solution(int n, int[][] edges, int src, int dest) {
        List<int[]>[] graph = new List[n + 1];
        IntStream.rangeClosed(0, n).forEach(i -> graph[i] = new ArrayList<>());

        for (int[] edge : edges) {
            int s = edge[0];
            int e = edge[1];
            int w = edge[2];
            graph[s].add(new int[]{e, w});
        }
        Arrays.stream(graph)
                .forEach(adj -> adj
                        .sort(Comparator.comparingInt(e -> e[1])));

        int[] distances = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        distances[src] = 0;

        PQ.add(new int[]{src, 0});
        while (!PQ.isEmpty()) {
            int[] city = PQ.poll();
            int cityNo = city[0];
            int dist = city[1];

            if (distances[cityNo] < dist) {
                continue;
            }

            for (int[] adjacent : graph[cityNo]) {
                int adjCity = adjacent[0];
                int cost = adjacent[1];
                if (distances[adjCity] > dist + cost) {
                    distances[adjCity] = dist + cost;
                    PQ.add(new int[]{adjCity, distances[adjCity]});
                }
            }
        }

        res.append(distances[dest]);
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