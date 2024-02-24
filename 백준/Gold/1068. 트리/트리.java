import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] parents = getInts();
        int delete = Integer.parseInt(br.readLine());

        solution(parents, delete);

        printRes();
    }

    private static void solution(int[] parents, int delete) {
        int root = -1;
        int n = parents.length;
        List<Integer>[] graph = IntStream.range(0, n)
                .mapToObj(ArrayList::new)
                .toArray(List[]::new);

        for (int i = 0; i < n; i++) {
            int parent = parents[i];
            if (i == delete || parent == delete) {
                continue;
            }
            if (parent == -1) {
                root = i;
                continue;
            }
            graph[parent].add(i);
        }

        int ans = getLeafCnt(root, graph);

        res.append(ans);
    }

    private static int getLeafCnt(int current, List<Integer>[] graph) {
        if (current == -1) {
            return 0;
        }
        if (graph[current].size() == 0) {
            return 1;
        }
        int leafCnt = 0;
        for (Integer adjacent : graph[current]) {
            leafCnt += getLeafCnt(adjacent, graph);
        }
        return leafCnt;
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