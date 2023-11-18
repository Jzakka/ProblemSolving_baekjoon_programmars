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
        int x = info[3];

        List<Integer>[] graph = new List[n + 1];
        IntStream.rangeClosed(1, n).forEach(i -> graph[i] = new ArrayList<>());
        for (int i = 0; i < m; i++) {
            int[] edge = getInts();
            graph[edge[0]].add(edge[1]);
        }

        solution(n, graph, k, x);

        printRes();
    }

    private static void solution(int n, List<Integer>[] graph, int k, int x) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> Q = new LinkedList<>();
        Q.add(x);
        visited[x] = true;

        int dist = 0;
        List<Integer> ans = new ArrayList<>();
        while (!Q.isEmpty()) {
            int qLen = Q.size();

            for (int i = 0; i < qLen; i++) {
                Integer poped = Q.poll();

                if (dist == k) {
                    ans.add(poped);
                    continue;
                }

                for (Integer incident : graph[poped]) {
                    if (!visited[incident]) {
                        visited[incident] = true;
                        Q.add(incident);
                    }
                }
            }

            dist++;
        }

        if (ans.isEmpty()) {
            res.append(-1);
            return;
        }

        Collections.sort(ans);

        ans.forEach(i -> res.append(i).append("\n"));
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