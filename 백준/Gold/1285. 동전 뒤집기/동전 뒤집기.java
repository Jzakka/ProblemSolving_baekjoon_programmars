import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final Map<Integer, Character> dir = new HashMap<>();

    static {
        dir.put(0, 'U');
        dir.put(1, 'L');
        dir.put(2, 'R');
        dir.put(3, 'D');
    }

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == 'H') {
                    coins[i] = (coins[i] << 1) + 1;
                } else {
                    coins[i] <<= 1;
                }
            }
        }

        solution(coins);

        printRes();
    }

    private static void solution(int[] coins) {
        int n = coins.length;

        int ans = combinate(n, coins, 0);

        res.append(ans);
    }

    private static int combinate(int n, int[] coins, int idx) {
        if (idx == n) {
            int tail = countTail(n, coins);
            return tail;
        }

        int ans = Integer.MAX_VALUE;
        // 현재 row를 flip
        coins[idx] ^= -1;
        ans = Math.min(ans, combinate(n, coins, idx + 1));
        // row 복원
        coins[idx] ^= Integer.MIN_VALUE;

        // flip안하고 진행
        ans = Math.min(ans, combinate(n, coins, idx + 1));

        return ans;
    }

    private static int countTail(int n, int[] coins) {
        int res = 0;
        int half = n / 2;

        for (int i = 0; i < n; i++) {
            int zero = 0;
            for (int j = 0; j < n; j++) {
                if ((coins[j] >> i & 1) == 0) {
                    zero++;
                }
            }

            if (zero > half) { // flip -> 현재 1이 0이된다.
                res += (n - zero);
            } else {
                res += zero;
            }
        }
        return res;
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
HHTT
THHT
HTTH
TTHH
 */