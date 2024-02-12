import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, 1, -1, 0};

    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int v = info[0];
        int e = info[1];

        long[][] edges = new long[e][];
        for (int i = 0; i < e; i++) {
            edges[i] = getLongs();
        }

        solution(v, edges);

        printRes();
    }

    private static void solution(int v, long[][] edges) {
        int[] groups = IntStream.rangeClosed(0, v).toArray();

        Arrays.sort(edges, Comparator.comparingLong(edge -> edge[2]));

        long ans = 0;
        for (long[] edge : edges) {
            int src = (int) edge[0];
            int dest = (int) edge[1];

            while (groups[src] != src) {
                src = groups[src];
            }
            while (groups[dest] != dest) {
                dest = groups[dest];
            }

            if (src != dest) {
                int child = Math.max(src, dest);
                int parent = Math.min(src, dest);

                groups[child] = parent;
                ans+= edge[2];
            }
        }

        res.append(ans);
    }

    private static boolean sameGroup(int[] groups, int a, int b) {
        while (groups[a] != a) {
            a = groups[a];
        }
        while (groups[b] != b) {
            b = groups[b];
        }
        return a == b;
    }
    /*
6 7
1 6 4
1 4 1
1 2 7
2 3 2
2 5 4
3 4 3
4 5 2
     */


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