import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        int c = Integer.parseInt(br.readLine());
        while (c-- > 0) {
            int[] info = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            String[] seats = new String[info[0]];

            for (int i = 0; i < seats.length; i++) {
                seats[i] = br.readLine();
            }

            solution(seats);
        }

        printRes();
    }

    private static void solution(String[] seats) {
        /**
         * DP[i][bits] : i번째열의 상태가 bits일 때, 최대 학생수
         */
        int[][] DP = new int[seats[0].length()][1 << seats.length];

        for (int i = 0; i < DP[0].length; i++) {
            if (available(seats, 0, i)) {
                DP[0][i] = count(seats, i);
            }
        }

        for (int i = 1; i < seats[0].length(); i++) {
            for (int curBits = 0; curBits < DP[0].length; curBits++) {
                if (available(seats, i, curBits)) {
                    int students = count(seats, curBits);

                    for (int prevBits = 0; prevBits < DP[0].length; prevBits++) {
                        if (available(seats, i - 1, prevBits)) {
                            if (((prevBits << 1) & curBits) == 0 && (prevBits & curBits) == 0 && ((prevBits >> 1) & curBits) == 0) {
                                DP[i][curBits] = Math.max(DP[i][curBits], DP[i - 1][prevBits] + students);
                            }
                        }
                    }
                }

            }
        }

        int ans = Arrays.stream(DP[DP.length - 1]).max().getAsInt();
        res.append(ans).append("\n");
    }

    private static int count(String[] seats, int bits) {
        int totalBits = seats.length;

        int students = 0;
        for (int j = 0; j < totalBits; j++) {
            int bit = bits >> j;

            if ((bit & 1) == 1) {
                students++;
            }
        }

        return students;
    }

    private static boolean available(String[] seats, int col, int bits) {
        int totalBits = seats.length;

        for (int j = 0; j < totalBits; j++) {
            int bit = bits >> j;

            if ((bit & 1) == 1 && seats[j].charAt(col) == 'x') {
                return false;
            }
        }

        return true;
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}