import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] parents = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(parents);

        printRes();
    }

    private static void solution(int[] parents) {
        int n = parents.length;
        List<Integer>[] childrenOf = new List[n];
        int[] DP = new int[n];

        IntStream.range(0, n).forEach(parent -> childrenOf[parent] = new ArrayList<>());

        for (int i = 1; i < n; i++) {
            int parent = parents[i];
            childrenOf[parent].add(i);
        }

        traversal(childrenOf, DP, 0);

        res.append(DP[0]);
    }

    private static int traversal(List<Integer>[] childrenOf, int[] DP, int current) {
        List<Integer> times = new ArrayList<>();
        for (Integer child : childrenOf[current]) {
            int time = traversal(childrenOf, DP, child);
            times.add(time);
        }

        if (!times.isEmpty()) {
            Collections.sort(times, Comparator.reverseOrder());
            DP[current] = IntStream.range(0, times.size()).map(idx -> times.get(idx) + idx + 1)
                    .max()
                    .getAsInt();
        }

        return DP[current];
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}