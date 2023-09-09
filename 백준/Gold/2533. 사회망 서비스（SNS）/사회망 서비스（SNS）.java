import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] friends = new int[n - 1][];
        for (int i = 0; i < n - 1; i++) {
            friends[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(n, friends);

        printRes();
    }

    private static void solution(int n, int[][] friends) {
        boolean[] DP = new boolean[1_000_001];
        List<Integer>[] adjacent = new List[n + 1];
        IntStream.range(1, n + 1).forEach(i -> adjacent[i] = new ArrayList());

        boolean[] nodes = new boolean[n + 1];
        for (int[] friend : friends) {
            nodes[friend[1]] = true;
            adjacent[friend[0]].add(friend[1]);
            adjacent[friend[1]].add(friend[0]);
        }

        boolean[] visited = new boolean[n + 1];
        dfs(DP, adjacent, visited, 1);

        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            if (DP[i]) {
                cnt++;
            }
        }

        res.append(cnt);
    }

    private static boolean dfs(boolean[] dp, List<Integer>[] adjacent, boolean[] visited, int current) {
        visited[current] = true;
        if (adjacent[current].isEmpty()) {
            dp[current] = false;
            return false;
        }

        boolean isEarlyAdaptor = true;
        for (Integer child : adjacent[current]) {
            if (!visited[child]) {
                isEarlyAdaptor = dfs(dp, adjacent, visited, child) && isEarlyAdaptor;
            }
        }
        dp[current] = !isEarlyAdaptor;
        return dp[current];
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}