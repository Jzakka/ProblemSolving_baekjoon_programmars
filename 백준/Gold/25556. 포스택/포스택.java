import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};


    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());
        int[] seq = getInts();

        solution(seq);

        printRes();
    }

    private static void solution(int[] seq) {
        int[] stks = new int[4];
        Arrays.fill(stks, 0);

        for (int num : seq) {
            int selectedIdx = -1;
            int selectedTop = Integer.MIN_VALUE;

            for (int i = 0; i < 4; i++) {
                if (stks[i] < num && selectedTop < stks[i]) {
                    selectedIdx = i;
                    selectedTop = stks[i];
                }
            }

            if (selectedIdx == -1) {
                res.append("NO");
                return;
            }
            stks[selectedIdx] = num;
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
/*
4
3
1
1
1
 */