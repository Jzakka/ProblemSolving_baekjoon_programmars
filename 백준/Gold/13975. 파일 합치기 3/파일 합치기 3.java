import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            int[] files = getInts();

            solution(files);
        }

        printRes();
    }

    private static void solution(int[] files) {
        PriorityQueue<Long> PQ = new PriorityQueue<>();
        for (int file : files) {
            PQ.add((long) file);
        }

        long ans = 0;
        while (PQ.size() > 1) {
            long merge = PQ.poll() + PQ.poll();

            ans += merge;
            PQ.add(merge);
        }
        res.append(ans).append("\n");
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