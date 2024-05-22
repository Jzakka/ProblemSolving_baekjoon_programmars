import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, 1, -1, 0};
    private static final int[] jx = {-2, -1, 1, 2, 2, 1, -1, -2};
    private static final int[] jy = {1, 2, 2, 1, -1, -2, -2, -1};

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] edges = new int[m][];
        for (int i = 0; i < m; i++) {
            edges[i] = getInts();
        }
        int[] course = getInts();
        int start = course[0];
        int end = course[1];

        int[] ans = solution(n, edges,start, end);

        int minCost = ans[0];
        int cityCounts = ans[1];
        int[] cities = Arrays.copyOfRange(ans, 2, ans.length);
        res.append(minCost).append("\n");
        res.append(cityCounts).append("\n");
        for (int city : cities) {
            res.append(city).append(" ");
        }

        bw.write(res.toString());
        bw.close();
    }

    private static int[] solution(int n, int[][] edges, int start, int end) {
        List<int[]>[] graph = IntStream.rangeClosed(0, n).mapToObj(i -> new ArrayList<>()).toArray(List[]::new);

        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            int cost = edge[2];

            graph[src].add(new int[]{dest, cost});
        }

        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        int[] parents = IntStream.rangeClosed(0, n).toArray();
        parents[start] = -1;
        distance[start] = 0;
        PQ.add(new int[]{start, 0});
        while (!PQ.isEmpty()) {
            int[] polled = PQ.poll();
            int node = polled[0];
            int dist = polled[1];

            if (dist > distance[node]) {
                continue;
            }

            for (int[] adjacent : graph[node]) {
                int nextNode = adjacent[0];
                int cost = adjacent[1];

                if (dist + cost < distance[nextNode]) {
                    distance[nextNode] = dist + cost;
                    PQ.add(new int[]{nextNode, distance[nextNode]});
                    parents[nextNode] = node;
                }
            }
        }

        int minCost = distance[end];
        Collection<Integer> paths = getPaths(parents, end);

        List<Integer> ans = new ArrayList<>();
        ans.add(minCost);
        ans.add(paths.size());
        ans.addAll(paths);

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private static Collection<Integer> getPaths(int[] parents, int end) {
        Deque<Integer> dq = new ArrayDeque<>();
        int current = end;
        while (current != -1) {
            dq.offerFirst(current);
            current = parents[current];
        }

        return dq;
    }

    public static int[] getInts() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }

        return ints;
    }

    public static long[] getLongs() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        long[] longs = new long[n];
        for (int i = 0; i < n; i++) {
            longs[i] = Long.parseLong(st.nextToken());
        }

        return longs;
    }
}