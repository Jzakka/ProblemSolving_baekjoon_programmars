import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        long[] holes = getLongs();

        long ans = solution(holes);

        res.append(ans);

        bw.write(res.toString());
        bw.close();
    }

    private static long solution(long[] holes) {
        if (holes.length == 1) {
            return Math.max(holes[0], 1);
        }
        int[] mainHoles = new int[holes.length];

        long ans = 0;
        for (int i = 0; i < holes.length; i++) {
            ans += holes[i];
            if (holes[i] == 0) {
                mainHoles[i] = 1;
            }
        }

        int mainHolesCount = getMainHolesCount(mainHoles);

        return ans + mainHolesCount;
    }

    private static int getMainHolesCount(int[] mainHoles) {
        int zeroStartCnt = getZeroStartCnt(mainHoles);
        if (mainHoles[0] == 1) {
            int oneStartCnt = getOneStartCnt(mainHoles);
            return Math.max(zeroStartCnt, oneStartCnt);
        }
        return zeroStartCnt;
    }

    private static int getZeroStartCnt(int[] mainHoles) {
        int[][] DP = new int[mainHoles.length][2];
        DP[0][0] = 0;
        DP[0][1] = Integer.MIN_VALUE;

        for (int i = 1; i < DP.length; i++) {
            DP[i][0] = Math.max(DP[i - 1][0], DP[i - 1][1]);
            if (mainHoles[i] == 1) {
                DP[i][1] = DP[i - 1][0] + 1;
            } else {
                DP[i][1] = Integer.MIN_VALUE;
            }
        }

        return Math.max(DP[mainHoles.length - 1][0], DP[mainHoles.length - 1][1]);
    }

    private static int getOneStartCnt(int[] mainHoles) {
        int[][] DP = new int[mainHoles.length][2];
        DP[0][0] = Integer.MIN_VALUE;
        DP[0][1] = 1;

        for (int i = 1; i < DP.length; i++) {
            DP[i][0] = Math.max(DP[i - 1][0], DP[i - 1][1]);
            if (mainHoles[i] == 1) {
                DP[i][1] = DP[i - 1][0] + 1;
            } else {
                DP[i][1] = Integer.MIN_VALUE;
            }

            if (i == DP.length - 1) {
                DP[i][1] = Integer.MIN_VALUE;
            }
        }

        return Math.max(DP[mainHoles.length - 1][0], DP[mainHoles.length - 1][1]);
    }

    private static int saveOdd(int[] holes) {
        for (int i = 0; i < holes.length; i++) {
            if ((i & 1) == 0) {
                holes[i] = 0;
            }
        }
        return Arrays.stream(holes).sum();
    }

    private static int saveEven(int[] holes, int killIdx) {
        for (int i = 0; i < holes.length; i++) {
            if ((i & 1) == 1) {
                holes[i] = 0;
            }
        }
        holes[killIdx] = 0;
        return Arrays.stream(holes).sum();
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