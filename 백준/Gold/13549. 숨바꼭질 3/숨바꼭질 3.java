import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int src = info[0];
        int dest = info[1];
        solution(src, dest);

        printRes();
    }

    private static void solution(int src, int dest) {
        Deque<int[]> dq = new ArrayDeque<>();

        dq.addFirst(new int[]{src, 0});

        int[] dist = new int[100_001];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        while (!dq.isEmpty()) {
            int[] poped = dq.pollFirst();
            int pos = poped[0];
            int weight = poped[1];

            if (weight > dist[pos]) {
                continue;
            }
            if (pos == dest) {
                break;
            }

            if (pos + 1 <= 100_000 && dist[pos + 1] > weight + 1) {
                dist[pos + 1] = weight + 1;
                dq.addLast(new int[]{pos + 1, dist[pos + 1]});
            }
            if (pos - 1 >= 0 && dist[pos - 1] > weight + 1) {
                dist[pos - 1] = weight + 1;
                dq.addLast(new int[]{pos - 1, dist[pos - 1]});
            }
            if (pos * 2 <= 100_000 && dist[pos * 2] > weight) {
                dist[pos * 2] = weight;
                dq.addFirst(new int[]{pos * 2, dist[pos * 2]});
            }
        }

        res.append(dist[dest]);
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