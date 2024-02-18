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
        int[] info = getInts();
        int n = info[0];
        int l = info[1];
        int r = info[2];

        int[][] world = new int[n][];
        for (int i = 0; i < n; i++) {
            world[i] = getInts();
        }

        solution(n, l, r, world);

        printRes();
    }

    private static void solution(int n, int l, int r, int[][] world) {
        int ans = 0;
        while (merge(n, l, r, world)) {
            ans++;
        }
        res.append(ans);
    }

    private static boolean merge(int n, int l, int r, int[][] world) {
        List<Integer>[] graph = buildGraph(n, l, r, world);

        boolean equalized = equalize(graph, n, world);

        return equalized;
    }

    private static boolean equalize(List<Integer>[] graph, int n, int[][] world) {
        boolean[][] visited = new boolean[n][n];

        boolean equalized = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    SumAndCount sumAndCount = new SumAndCount();
                    getSumAndCount(sumAndCount, visited,graph, n, i, j, world);
                    if (sumAndCount.nodes.size() == 1) {
                        continue;
                    }
                    equalized = true;
                    int avg = sumAndCount.getAvg();
                    for (Integer node : sumAndCount.nodes) {
                        Pair pair = unionCoord(node, n);
                        world[pair.x][pair.y] =avg;
                    }
                }
            }
        }
        return equalized;
    }

    private static void getSumAndCount(SumAndCount sumAndCount, boolean[][] visited, List<Integer>[] graph, int n, int x, int y, int[][] world) {
        visited[x][y] = true;

        sumAndCount.sum += world[x][y];
        int node = unionSeq(x, y, n);
        sumAndCount.nodes.add(node);

        for (Integer adjacent : graph[node]) {
            Pair pair = unionCoord(adjacent, n);
            if (!visited[pair.x][pair.y]) {
                getSumAndCount(sumAndCount, visited, graph, n, pair.x, pair.y, world);
            }
        }
    }

    private static List<Integer>[] buildGraph(int n, int l, int r, int[][] world) {
        List<Integer>[] graph = new List[n * n];
        IntStream.range(0, n * n).forEach(i -> graph[i] = new ArrayList<>());

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (inRange(i, j - 1, n) && mergeable(l, r, world[i][j], world[i][j - 1])) {
                    graph[unionSeq(i, j, n)].add(unionSeq(i, j - 1, n));
                    graph[unionSeq(i, j - 1, n)].add(unionSeq(i, j, n));
                }
                if (inRange(i - 1, j, n) && mergeable(l, r, world[i][j], world[i - 1][j])) {
                    graph[unionSeq(i, j, n)].add(unionSeq(i - 1, j, n));
                    graph[unionSeq(i - 1, j, n)].add(unionSeq(i, j, n));
                }
            }
        }

        return graph;
    }

    private static Pair unionCoord(int seq, int n) {
        int q = seq / n;
        return new Pair(q, seq - n * q);
    }

    private static int unionSeq(int x, int y, int n) {
        return x * n + y;
    }

    private static boolean mergeable(int l, int r, int pop1, int pop2) {
        int diff = Math.abs(pop1 - pop2);
        return l <= diff && diff <= r;
    }

    private static boolean inRange(int x, int y, int n) {
        return 0 <= x && x < n && 0 <= y && y < n;
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