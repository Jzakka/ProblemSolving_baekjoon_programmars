import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[][] tasks = new int[n][];

        for (int i = 0; i < n; i++) {
            tasks[i] = getInts();
        }

        int[] ans = solution(tasks);

        for (int e : ans) {
            res.append(e).append(" ");
        }

        bw.write(res.toString());
        bw.close();
    }

    private static int[] solution(int[][] tasks) {
        return IntStream.range(0, tasks.length).boxed()
                .sorted((i1, i2) -> {
                    int[] t1 = tasks[i1];
                    int[] t2 = tasks[i2];

                    int risk1 = t1[0] * t2[1];
                    int risk2 = t2[0] * t1[1];

                    if (risk1 == risk2) {
                        return i1 - i2;
                    }
                    return risk1 - risk2;
                })
                .mapToInt(i -> i + 1)
                .toArray();
    }

    public static int[] getInts() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = Integer.parseInt(st.nextToken());
        }

        return ints;
    }

    public static long[] getLongs() throws IOException {
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        int n = st.countTokens();
        long[] longs = new long[n];
        for (int i = 0; i < n; i++) {
            longs[i] = Long.parseLong(st.nextToken());
        }

        return longs;
    }
}