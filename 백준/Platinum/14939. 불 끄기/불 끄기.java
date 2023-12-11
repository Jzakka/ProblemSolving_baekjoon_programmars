import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] square = new int[10];

        for (int i = 0; i < 10; i++) {
            String row = br.readLine();
            for (int j = 0; j < row.length(); j++ ) {
                square[i] <<= 1;

                if (row.charAt(j) == 'O') {
                    square[i]++;
                }
            }
        }

        solution(square);

        printRes();
    }

    private static void solution(int[] square) {
        // stateBit = 맨 첫번째 행에서 어떤 열을 플롭할지 정해놓음
        int[][] states = new int[2 << 10][10];
        // 각 상태별로 모두 복사
        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < 10; j++) {
                states[i][j] = square[j];
            }
        }

        int ans = Integer.MAX_VALUE;
        for (int state = 0; state < states.length; state++) {
            int count = calc(state, states[state]);
            if (count >= 0) {
                ans = Math.min(ans, count);
            }
        }

        if (ans == Integer.MAX_VALUE) {
            res.append(-1);
        } else {
            res.append(ans);
        }

    }

    private static int calc(int stateBits, int[] matrix) {
        int cnt = 0;
        for (int j = 0; j < 10; j++) {
            if ((stateBits >> j & 1) == 1) {
                crossFlip(matrix, 0, j);
                cnt++;
            }
        }

        for (int i = 1; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((matrix[i - 1] >> j & 1) == 1) {
                    cnt++;
                    crossFlip(matrix, i, j);
                }
            }
        }

        for (int row : matrix) {
            if (row > 0) {
                return -1;
            }
        }
        return cnt;
    }

    private static void crossFlip(int[] matrix, int i, int j) {
        if (i - 1 >= 0) {
            matrix[i - 1] ^= (1 << j);
        }

        if (j - 1 >= 0) {
            matrix[i] ^= (1 << (j - 1));
        }
        matrix[i] ^= (1 << j);
        if (j + 1 < 10) {
            matrix[i] ^= (1 << (j + 1));
        }

        if (i + 1 < 10) {
            matrix[i + 1] ^= (1 << j);
        }
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
##########
##########
######O###
##########
####O#O#O#
##########
######O###
##########
O##O##O##O
OOOOOOOOOO
 */