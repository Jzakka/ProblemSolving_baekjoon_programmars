import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = getInts();
        int n = info[0];
        int k = info[1];
        int[] passengers = getInts();

        solution(n, k, passengers);

        printRes();
    }

    private static void solution(int n, int k, int[] passengers) {
        ArrayDeque<Integer>[] stk = new ArrayDeque[k + 1];
        IntStream.rangeClosed(1, k).forEach(i -> {
            stk[i] = new ArrayDeque<>();
            stk[i].offerLast(0);
        });
        stk[0] = new ArrayDeque<>();
        stk[0].offerLast(-1);

        for (int passenger : passengers) {
            int selectQ = 0;

            for (int i = 1; i <= k; i++) {
                if (stk[i].peekLast() < passenger &&
                        passenger - stk[i].peekLast() < passenger - stk[selectQ].peekLast()) {
                    selectQ = i;
                }
            }

            if (selectQ == 0) {
                res.append("NO");
                return;
            }

            stk[selectQ].offerLast(passenger);
        }

        res.append("YES");
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