import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final long MOD = 1_000_000_007;
    private static StringBuilder res = new StringBuilder();
    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            br.readLine();
            long[] arr = getLongs();

            solution(arr);
        }

        printRes();
    }

    private static void solution(long[] arr) {
        long ans = 1;

        PriorityQueue<Long> PQ = new PriorityQueue<>(Arrays.stream(arr).boxed().collect(Collectors.toList()));

        while (PQ.size() > 1) {
            Long min1 = PQ.poll();
            Long min2 = PQ.poll();
            Long mul = (min1 * min2);
            ans *= mul % MOD;
            ans %= MOD;
            PQ.add(mul);
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

/*
5
1 3 2 4 3

5
1 3 4 4 5
 */