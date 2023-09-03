import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] sequence = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        solution(n,sequence);

        printRes();
    }

    private static void solution(int n, int[] sequence) {
        int[] DP = new int[n];
        int[] prev = new int[n];

        List<Integer> length = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int num = sequence[i];

            if (!length.isEmpty() && sequence[length.get(length.size() - 1)] >= num) {
                int insertIdx = lowerBound(sequence, length, num);
                DP[i] = insertIdx;
                length.set(insertIdx, i);
            } else {
                DP[i] = length.size();
                length.add(i);
            }

            if (DP[i] == 0) {
                prev[i] = -1;
            } else {
                prev[i] = length.get(DP[i] - 1);
            }
        }

        int idx = -1;
        int maxDP = -1;

        for (int i = 0; i < n; i++) {
            if (DP[i] > maxDP) {
                maxDP = DP[i];
                idx = i;
            }
        }

        res.append(length.size()).append("\n");

        Deque<Integer> dq = new ArrayDeque<>();

        while (dq.size() < length.size()) {
            int traceNum = sequence[idx];
            dq.offerFirst(traceNum);
            idx = prev[idx];
        }

        dq.stream().forEach(num -> res.append(num).append(" "));
    }

    private static int lowerBound(int[] sequence, List<Integer> length, int num) {
        int s, e;
        s = 0;
        e = length.size();

        while (s < e) {
            int m = (s + e) / 2;
            if (sequence[length.get(m)] < num) {
                s = m + 1;
            } else {
                e = m;
            }
        }

        return e;
    }


    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}