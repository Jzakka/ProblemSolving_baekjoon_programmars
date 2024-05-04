import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[] graph = getInts();

            solution(n, graph);
        }

        bw.write(res.toString());
        bw.close();
    }

    private static void solution(int n, int[] graph) {
        boolean[] visited = new boolean[n];

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int idx = i - 1;

            if (!visited[idx]) {
                travel(visited, graph, idx);
                ans++;
            }
        }

        res.append(ans).append("\n");
    }

    private static void travel(boolean[] visited, int[] graph, int curIdx) {
        while (!visited[curIdx]) {
            visited[curIdx] = true;
            int next = graph[curIdx];
            int nextIdx = next - 1;
            curIdx = nextIdx;
        }
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
}
