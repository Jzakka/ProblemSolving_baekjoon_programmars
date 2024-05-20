import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, 1, -1, 0};
    private static final int AIR = 2;
    private static final int CHEESE = 1;
    private static final int SPACE = 0;

    public static void main(String[] args) throws IOException {
        int[] info = getInts();
        int n = info[0];
        int m = info[1];

        int[][] edges = new int[m][];
        for (int i = 0; i < m; i++) {
            edges[i] = getInts();
        }

        long[] ans = solution(n, edges);

        String joinedResult = Arrays.stream(ans)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining("\n"));
        res.append(joinedResult);

        bw.write(res.toString());
        bw.close();
    }

    private static long[] solution(int n, int[][] edges) {
        long[] distance = new long[n + 1];
        Arrays.fill(distance, Long.MAX_VALUE);

        boolean noCycle = bellmanFord(distance, edges, 1);

        List<Long> ans = new ArrayList<>();
        if (noCycle) {
            for (int i = 2; i <= n; i++) {
                ans.add(distance[i] == Long.MAX_VALUE ? -1 : distance[i]);
            }
            return ans.stream().mapToLong(Long::longValue).toArray();
        }
        return new long[]{-1};
    }

    public static boolean bellmanFord(long[] distance, int[][] edges, int start) {
        int v = distance.length-1;
        distance[start] = 0;
        for (int i = 0; i < v; i++) {
            for (int[] edge : edges) {
                int src = edge[0];
                int dest = edge[1];
                int cost = edge[2];

                if (distance[src] != Long.MAX_VALUE && distance[src] + cost < distance[dest]) {
                    distance[dest] = distance[src] + cost;
                    if (i == v - 1) {
                        return false;
                    }
                }
            }
        }

        return true;
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