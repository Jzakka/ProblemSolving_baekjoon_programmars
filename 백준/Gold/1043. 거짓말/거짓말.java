import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] firstLine = getInts();
        int n = firstLine[0];
        int m = firstLine[1];

        int[] secondLine = getInts();
        int[] truth;
        if (secondLine[0] == 0) {
            truth = new int[0];
        } else {
            truth = Arrays.copyOfRange(secondLine, 1, secondLine.length);
        }

        int[][] parties = new int[m][];
        for (int i = 0; i < m; i++) {
            int[] repeatLine = getInts();
            parties[i] = Arrays.copyOfRange(repeatLine, 1, repeatLine.length);
        }

        solution(n, truth, parties);

        printRes();
    }

    private static void solution(int n, int[] truth, int[][] parties) {
        if (truth.length == 0) {
            res.append(parties.length);
            return;
        }
        boolean[] known = new boolean[n + 1];

        Set<Integer>[] graph = IntStream.rangeClosed(0, n)
                .mapToObj(HashSet::new)
                .toArray(Set[]::new);
        for (int i = 1; i < truth.length; i++) {
            int first = truth[0];
            graph[first].add(truth[i]);
            graph[truth[i]].add(first);
        }

        for (int[] party : parties) {
            for (int i = 1; i < party.length; i++) {
                int first = party[0];
                graph[first].add(party[i]);
                graph[party[i]].add(first);
            }
        }

        dfs(graph, known, truth[0]);

        int ans = 0;
        for (int[] party : parties) {
            if (Arrays.stream(party).noneMatch(member -> known[member])) {
                ans++;
            }
        }
        res.append(ans);
    }

    /*
10 10
4 1 2 3 4
2 1 5
2 2 6
1 7
1 8
2 7 8
1 9
1 10
2 3 10
1 4
2 8 10
     */

    private static void dfs(Set<Integer>[] graph, boolean[] known, int cur) {
        known[cur] = true;

        for (Integer adj : graph[cur]) {
            if (!known[adj]) {
                dfs(graph, known, adj);
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