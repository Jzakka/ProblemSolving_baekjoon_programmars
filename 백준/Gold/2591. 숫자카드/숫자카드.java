import java.io.*;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        String seq = br.readLine();

        solution(seq);

        printRes();
    }

    private static void solution(String seq) {
        // DP[i][j]: i번째 숫자가 j로 끝나는 경우의 수
        long[][] DP = new long[seq.length()][10];

        int firstDigit = seq.charAt(0) - '0';
        DP[0][firstDigit] = 1;

        if (seq.length() < 2) {
            res.append(DP[0][firstDigit]);
            return;
        }

        for (int i = 1; i < seq.length(); i++) {
            int digit = seq.charAt(i) - '0';
            int prevDigit = seq.charAt(i - 1) - '0';

            if (digit != 0) {
                DP[i][digit] = Arrays.stream(DP[i - 1]).sum();
            }

            if (digit <= 4) {
                if (prevDigit == 1 || prevDigit == 2 || prevDigit == 3) {
                    if (i - 2 >= 0) {
                        DP[i][digit] += Arrays.stream(DP[i - 2]).sum();
                    } else {
                        DP[i][digit]++;
                    }
                }
            } else {
                if (prevDigit == 1 || prevDigit == 2) {
                    if (i - 2 >= 0) {
                        DP[i][digit] += Arrays.stream(DP[i - 2]).sum();
                    } else {
                        DP[i][digit]++;
                    }
                }
            }
        }

        res.append(DP[DP.length - 1][seq.charAt(seq.length() - 1) - '0']);
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
3 1 6 5 4 5 2

 */