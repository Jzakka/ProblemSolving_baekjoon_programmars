import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int[] info = getInts();
        int m = info[0];
        int n = info[1];

        int[] friends = new int[n];
        for (int i = 0; i < n; i++) {
            friends[i] = Integer.parseInt(br.readLine());
        }

        long ans = solution(m, friends);

        res.append(ans);

        bw.write(res.toString());
        bw.close();
    }

    private static long solution(int m, int[] friends) {
        int n = friends.length;
        Arrays.sort(friends);
        long remain = Arrays.stream(friends).mapToLong(i -> i).sum() - m;
        final long MOD = (long) Math.pow(2, 64);

        long ans = 0;
        for (int i = 0; i < n && remain > 0; i++) {
            long need = friends[i];

            long allocate = Math.min(need, remain / (n - i));

            remain -= allocate;
            ans += allocate * allocate;
            ans %= MOD;
        }
        return ans;
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