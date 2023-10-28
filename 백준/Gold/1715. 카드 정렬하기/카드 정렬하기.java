import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        long[] cards = new long[n];

        for (int i = 0; i < n; i++) {
            cards[i] = Integer.parseInt(br.readLine());
        }

        solution(cards);

        printRes();
    }

    private static void solution(long[] cards) {
        PriorityQueue<Long> PQ = new PriorityQueue<>();

        for (long card : cards) {
            PQ.add(card);
        }

        long ans = 0;
        while (PQ.size() > 1) {
            Long first = PQ.poll();
            Long second = PQ.poll();

            ans += first + second;

            PQ.add(first + second);
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

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}