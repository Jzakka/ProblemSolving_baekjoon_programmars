import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final long MOD = 1_000_000_007;
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[][] lines = new int[n][];

        for (int i = 0; i < n; i++) {
            lines[i] = getInts();
        }

        solution(lines);

        printRes();
    }

    private static void solution(int[][] lines) {
        Arrays.sort(lines, (l1, l2) -> {
            if (l1[0] == l2[0]) {
                return l1[1] - l2[1];
            }
            return l1[0] - l2[0];
        });

        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.comparingInt(l -> l[1]));

        int ans = 0;
        for (int[] line : lines) {
            while (!PQ.isEmpty() && PQ.peek()[1] <= line[0]) {
                PQ.poll();
            }
            PQ.add(line);
            ans = Math.max(ans, PQ.size());
        }

        res.append(ans);
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