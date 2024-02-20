import java.io.*;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};


    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        List<int[]>[] graph = IntStream.rangeClosed(0, n)
                .mapToObj(i -> new ArrayList())
                .toArray(List[]::new);
        for (int i = 0; i < n; i++) {
            int[] edges = getInts();

            int j = 1;
            while (j < edges.length) {
                int dest = edges[j];
                if (dest == -1) {
                    break;
                }
                int cost = edges[j + 1];

                graph[edges[0]].add(new int[]{dest, cost});
                j += 2;
            }
        }
        solution(graph);

        printRes();
    }

    static class IntRef {
        int val = 0;
    }

    private static void solution(List<int[]>[] graph) {
        IntRef ans = new IntRef();

        traversal(graph, new HashSet<>(), 1, ans);

        res.append(ans.val);
    }

    /**
     * @param graph
     * @param visited
     * @param cur
     * @param ans
     * @return 자식가는 cost + 자식 반환값 중 젤루 큰놈
     */
    private static int traversal(List<int[]>[] graph, Set<Integer> visited, int cur, IntRef ans) {
        visited.add(cur);

        List<Integer> childsRes = new ArrayList<>();
        for (int[] incident : graph[cur]) {
            int child = incident[0];
            int cost = incident[1];

            if (!visited.contains(child)) {
                int childRes = traversal(graph, visited, child, ans) + cost;
                childsRes.add(childRes);
            }
        }
        childsRes.sort(Comparator.comparingInt(Integer::intValue).reversed());

        int childLinkLength = 0;
        for (int i = 0; i < Math.min(2, childsRes.size()); i++) {
            childLinkLength += childsRes.get(i);
        }
        ans.val = Math.max(ans.val, childLinkLength);

        return childsRes.stream().findFirst().orElse(0);
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

/*
9
1 2 1 7 2 -1
2 1 1 3 30 4 22 -1
3 2 30 -1
4 2 22 5 7 6 10 -1
5 4 7 -1
6 4 10 -1
7 1 2 9 3 8 5 -1
8 7 5 -1
9 7 3 -1
 */