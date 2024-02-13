import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
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
        int m = info[1];

        int[][] pairs = new int[m][];
        for (int i = 0; i < m; i++) {
            pairs[i] = getInts();
        }

        solution(n, pairs);

        printRes();
    }

    private static void solution(int n, int[][] pairs) {
        List<Integer>[] graph = new List[n + 1];
        int[] indegrees = new int[n + 1];
        IntStream.rangeClosed(0, n).forEach(i -> graph[i] = new ArrayList<>());

        for (int[] pair : pairs) {
            int src = pair[0];
            int dest = pair[1];
            graph[src].add(dest);
            indegrees[dest]++;
        }

        List<Integer> init = IntStream.rangeClosed(1, n)
                .filter(i -> indegrees[i] == 0)
                .boxed()
                .collect(Collectors.toList());
        Deque<Integer> toVisit = new ArrayDeque<>(init);

        while (!toVisit.isEmpty()) {
            Integer visited = toVisit.pollFirst();
            res.append(visited).append(" ");

            for (Integer adjacent : graph[visited]) {
                if (--indegrees[adjacent] == 0) {
                    toVisit.offerLast(adjacent);
                }
            }
        }
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