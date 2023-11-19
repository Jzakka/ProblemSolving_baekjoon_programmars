import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] colleges = new int[n][];

        for (int i = 0; i < n; i++) {
            colleges[i] = getInts();
        }

        solution(colleges);

        printRes();
    }

    private static void solution(int[][] colleges) {
        HashMap<Integer, List<Integer>> dayAndProfit = new HashMap<>();
        for (int[] college : colleges) {
            int profit = college[0];
            int day = college[1];

            if (!dayAndProfit.containsKey(day)) {
                dayAndProfit.put(day, new ArrayList<>());
            }
            dayAndProfit.get(day).add(profit);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        int ans = 0;

        for (int day = 10000; day > 0; day--) {
            if (dayAndProfit.containsKey(day)) {
                pq.addAll(dayAndProfit.get(day));
            }

            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
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