import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] ints = getInts();
        solution(ints[0], ints[1]);

        printRes();
    }

    private static void solution(int src, int dest) {
        int[] prev = new int[100_001];
        Arrays.fill(prev, -2);

        Queue<Integer> Q = new LinkedList<>();
        Q.add(src);
        prev[src] = -1;

        int time = 0;
        while (!Q.isEmpty()) {
            int qLen = Q.size();

            for (int i = 0; i < qLen; i++) {
                Integer pos = Q.poll();

                if (pos == dest) {
                    result(dest, prev, time);
                    return;
                }

                if (pos - 1 >= 0 && prev[pos - 1] == -2) {
                    prev[pos - 1] = pos;
                    Q.add(pos - 1);
                }

                if (pos + 1 <= 100_000 && prev[pos + 1] == -2) {
                    prev[pos + 1] = pos;
                    Q.add(pos + 1);
                }

                if (2 * pos <= 100_000 && prev[2 * pos] == -2) {
                    prev[2 * pos] = pos;
                    Q.add(2 * pos);
                }
            }
            time++;
        }
    }

    private static void result(int dest, int[] prev, int time) {
        res.append(time).append("\n");
        int cur = dest;
        Deque<Integer> dq = new ArrayDeque<>();
        while (cur != -1) {
            dq.offerFirst(cur);
            cur = prev[cur];
        }
        dq.forEach(num -> res.append(num).append(" "));
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