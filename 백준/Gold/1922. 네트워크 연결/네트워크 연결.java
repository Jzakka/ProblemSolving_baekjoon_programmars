import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();


    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] edges = new int[m][];

        for (int i = 0; i < m; i++) {
            edges[i] = getInts();
        }

        solution(n, edges);

        printRes();
    }

    private static void solution(int n, int[][] edges) {
        int[] group = IntStream.rangeClosed(0, n).toArray();

        Arrays.sort(edges, Comparator.comparingInt(edge -> edge[2]));

        int ans = 0;
        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            int cost = edge[2];

            int leader1 = getGroupLeader(group, src);
            int leader2 = getGroupLeader(group, dest);

            if (leader1 != leader2) {
                ans += cost;
                group[leader1] = leader2;
            }
        }

        res.append(ans);
    }

    private static int getGroupLeader(int[] group, int node) {
        while (group[node] != node) {
            node = group[node];
        }
        return node;
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

    private static class SumAndCount {
        int sum = 0;

        List<Integer> nodes = new ArrayList<>();

        int getAvg() {
            return sum / nodes.size();
        }
    }

    private static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}