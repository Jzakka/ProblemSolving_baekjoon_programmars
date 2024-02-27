import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] graph = new int[n][];
        for (int i = 0; i < n; i++) {
            graph[i] = getInts();
        }
        int[] cities = getInts();

        solution(n, m, graph, cities);

        printRes();
    }

    private static void solution(int n, int m, int[][] graph, int[] cities) {
        int[] parent = IntStream.rangeClosed(0, n).toArray();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int isLinked = graph[i][j];
                if (isLinked == 1) {
                    int p1 = getParent(parent, i+1);
                    int p2 = getParent(parent, j+1);
                    parent[p1] = p2;
                }
            }
        }

        int components = Arrays.stream(cities)
                .map(city -> getParent(parent, city))
                .boxed()
                .collect(Collectors.groupingBy(Function.identity()))
                .size();
        res.append(components == 1 ? "YES" : "NO");
    }

    private static int getParent(int[] parent, int city) {
        while (parent[city] != city) {
            city = parent[city];
        }
        return city;
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