import java.io.*;
import java.util.*;
import java.util.function.Function;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static final int SIX = 5;
    private static final int FIVE = 4;
    private static final int FOUR = 3;
    private static final int THREE = 2;
    private static final int TWO = 1;
    private static final int ONE = 0;


    public static void main(String[] args) throws Exception {
        int[] squares = new int[6];

        for (int i = 0; i < 6; i++) {
            squares[i] = Integer.parseInt(br.readLine());
        }

        solution(squares);

        printRes();
    }

    private static void solution(int[] squares) {
        int cnt = 0;

        // 6인 종이 소비
        cnt += squares[SIX];

        // 5인 종이 소비
        cnt += squares[FIVE];
        minus(squares, ONE, squares[FIVE] * 11); // 짜투리 1

        // 4인 종이 소비
        cnt += squares[FOUR];
        int q4 = squares[TWO] / 5;
        if (q4 >= squares[FOUR]) {
            minus(squares, TWO, squares[FOUR] * 5);
        } else {
            int r = squares[TWO] - 5 * q4;
            minus(squares, TWO, 100); // 2를 전부 사용
            minus(squares, ONE, 20 * (squares[FOUR] - q4 - 1));
            minus(squares, ONE, 36 - 16 - r * 4);
        }

        // 3인 종이 소비
        int q3 = squares[THREE] / 4;
        int r3 = squares[THREE] - q3 * 4;
        cnt += q3;
        if (r3 > 0) {
            cnt++;
            int twoCnt = 0;
            int oneCnt;
            switch (r3) {
                case 1:
                    twoCnt = Math.min(squares[TWO], 5);
                    break;
                case 2:
                    twoCnt = Math.min(squares[TWO],3);
                    break;
                case 3:
                    twoCnt = Math.min(squares[TWO],1);
                    break;
                default:
            }
            oneCnt = 36 - 9 * r3 - 4 * twoCnt;
            minus(squares, TWO, twoCnt);
            minus(squares, ONE, oneCnt);
        }

        // 2인 종이 소비
        int q2 = squares[TWO] / 9;
        int r2 = squares[TWO] - 9 * q2;
        cnt += q2;
        if (r2 > 0) {
            cnt++;
            int oneCnt = 36 - 4 * r2;
            minus(squares, ONE, oneCnt);
        }

        int q1 = squares[ONE] / 36;
        int r1 = squares[ONE] - 36 * q1;
        cnt += q1;
        if (r1 > 0) {
            cnt++;
        }

        res.append(cnt);
    }

    private static void minus(int[] squares, int square, int cnt) {
        squares[square] -= cnt;
        if (squares[square] < 0) {
            squares[square] = 0;
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
58
3
5
1
2
0

37
0
0
0
0
7

17
20
0
3
0
0

13
12
0
3
0
0

7
6
5
0
0
0

49
7
0
2
0
0

32
7
0
3
0
0
 */