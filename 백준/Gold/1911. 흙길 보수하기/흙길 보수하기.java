import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        long[] info = getLongs();
        long n = info[0];
        long l = info[1];

        long[][] muds = new long[(int) n][];
        for (int i = 0; i < n; i++) {
            muds[i] = getLongs();
        }

        solution(l, muds);

        printRes();
    }

    private static void solution(long l, long[][] muds) {
        Arrays.sort(muds, Comparator.comparingLong(m -> m[0]));

        long lastE = 0;
        long ans = 0;

        for (long[] mud : muds) {
            long s = mud[0];
            long e = mud[1];

            long cnt;
            if (s <= lastE) {
                cnt = (e - lastE) / l;
                if (cnt * l < (e - lastE)) {
                    cnt++;
                }
            } else {
                cnt = (e - s) / l;
                if (cnt * l < (e - s)) {
                    cnt++;
                }
                lastE = s;
            }
            ans += cnt;
            lastE += cnt * l;
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