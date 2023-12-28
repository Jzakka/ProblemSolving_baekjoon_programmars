import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final long MOD = 1_000_000_007;
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        long[][] coord = new long[n][];
        for (int i = 0; i < n; i++) {
            coord[i] = getLongs();
        }
        solution(coord);
        printRes();
    }

    private static void solution(long[][] coord) {
        long[] ans = coord[0];
        long minDist = Integer.MAX_VALUE;
        
        for (long[] minCoord : coord) {
            long currentMaxDist = 0;
            for (long[] curCoord : coord) {
                if (curCoord == minCoord) {
                    continue;
                }
                long dist = (long) (Math.pow(minCoord[0] - curCoord[0], 2) +
                                        Math.pow(minCoord[1] - curCoord[1], 2));
                if (currentMaxDist < dist) {
                    currentMaxDist = dist;
                }
            }

            if (minDist > currentMaxDist) {
                ans = minCoord;
                minDist = currentMaxDist;
            }
        }

        res.append(ans[0]).append(" ").append(ans[1]);
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
0111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111
0100 0011 1110 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000

0 | 100 0011 1110 | 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000

1.11 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 1111 * 2^62
62 + 1023

1.0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 0000 * 2^63
 */