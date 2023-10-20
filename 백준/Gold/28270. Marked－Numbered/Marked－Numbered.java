import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();


    public static void main(String[] args) throws Exception {
        br.readLine();

        int[] depths = getInts();

        solution(depths);

        printRes();
    }

    private static void solution(int[] depths) {
        Stack<Integer> stk = new Stack<>();
        int[] ans = new int[depths.length];

        for (int i = 1; i < depths.length; i++) {
            if (depths[i] > depths[i - 1] && depths[i] - depths[i - 1] > 1) {
                res.append(-1);
                return;
            }
        }

        for (int i = 0; i < depths.length; i++) {
            int num = 0;

            while (!stk.isEmpty() && depths[stk.peek()] > depths[i]) {
                stk.pop();
            }
            while (!stk.isEmpty() && depths[stk.peek()] == depths[i]) {
                num = ans[stk.pop()];
            }
            stk.push(i);

            ans[i] = num + 1;
        }

        Arrays.stream(ans).forEach(a -> res.append(a).append(" "));
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