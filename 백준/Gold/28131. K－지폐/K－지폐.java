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
        int k = info[2];
        int[] fromTo = getInts();

        List<int[]>[] graph = new List[n + 1];
        IntStream.rangeClosed(1, n).forEach(i -> graph[i] = new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int[] edge = getInts();
            int src = edge[0];
            int dest = edge[1];
            int weight = edge[2];

            graph[src].add(new int[]{dest, weight});
        }

        solution(n, graph, fromTo[0], fromTo[1], k);

        printRes();
    }

    private static void solution(int n, List<int[]>[] graph, int from, int to, int k) {
        int[][] dist = new int[n + 1][k];
        for (int i = 0; i < dist.length; i++) {
            for (int j = 0; j < dist[0].length; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.comparingInt(state -> dist[state[0]][state[1] % k]));
        PQ.add(new int[]{from, 0});
        dist[from][0] = 0;

        while (!PQ.isEmpty()) {
            int[] state = PQ.poll();

            int nodeNum = state[0];
            int minDist = state[1];

            if (dist[nodeNum][minDist % k] < minDist) {
                continue;
            }

            for (int[] edge : graph[nodeNum]) {
                int incident = edge[0];
                int weight = edge[1];


                int nextWeight = weight + minDist;
                int key = nextWeight % k;

                if (dist[incident][key] > nextWeight) {
                    dist[incident][key] = nextWeight;
                    PQ.add(new int[]{incident, nextWeight});
                }
            }
        }

        if (dist[to][0] == Integer.MAX_VALUE) {
            res.append("IMPOSSIBLE");
            return;
        }
        res.append(dist[to][0]);
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