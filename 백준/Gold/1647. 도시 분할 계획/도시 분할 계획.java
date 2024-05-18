import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int[] info = getInts();
        int n = info[0];
        int m = info[1];

        int[][] edges = new int[m][];

        for (int i = 0; i < m; i++) {
            edges[i] = getInts();
        }

        int ans = solution(n, edges);

        res.append(ans);

        bw.write(res.toString());
        bw.close();
    }

    private static int solution(int n, int[][] edges) {
        int[] leaders = IntStream.rangeClosed(0, n).toArray();

        Arrays.sort(edges, Comparator.comparingInt(edge -> edge[2]));

        int total = 0;
        int last = 0;
        for (int[] edge : edges) {
            int src = edge[0];
            int dest = edge[1];
            int cost = edge[2];

            int srcLeader = leader(leaders, src);
            int destLeader = leader(leaders, dest);

            if (srcLeader != destLeader) {
                total += cost;
                last = cost;

                if (srcLeader < destLeader) {
                    leaders[destLeader] = srcLeader;
                } else {
                    leaders[srcLeader] = destLeader;
                }
            }
        }

        return total - last;
    }

    private static int leader(int[] leaders, int member) {
        while (leaders[member] != member) {
            member = leaders[member];
        }
        return member;
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