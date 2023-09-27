import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int MOD = 1_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] video = new int[n][];
        for (int i = 0; i < n; i++) {
            video[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        solution(video);

        printRes();
    }

    private static void solution(int[][] video) {
        int n = video.length;
        StringBuilder ans = compress(video, 0, 0, n, n);

        res.append(ans);
    }

    private static StringBuilder compress(int[][] video, int x1, int y1, int x2, int y2) {
        if (x2 - x1 == 1 && y2 - y1 == 1) {
            return new StringBuilder(String.valueOf(video[x1][y1]));
        }

        StringBuilder nw = compress(video, x1, y1, (x1 + x2) / 2, (y1 + y2) / 2);
        StringBuilder ne = compress(video, x1,(y1 + y2) / 2, (x1 + x2) / 2,  y2);
        StringBuilder sw = compress(video, (x1 + x2) / 2, y1, x2, (y1 + y2) / 2);
        StringBuilder se = compress(video, (x1 + x2) / 2, (y1 + y2) / 2, x2, y2);

        if ("0".equals(nw.toString()) && "0".equals(ne.toString()) && "0".equals(sw.toString()) && "0".equals(se.toString())) {
            return nw; //"0"
        } else if ("1".equals(nw.toString()) && "1".equals(ne.toString()) && "1".equals(sw.toString()) && "1".equals(se.toString())) {
            return nw; //"1"
        }

        return new StringBuilder("(").append(nw).append(ne).append(sw).append(se).append(")");
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}