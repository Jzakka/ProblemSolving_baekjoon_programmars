import java.io.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine());

            solution(n);
        }

        printRes();
    }

    private static void solution(int n) {
        int[] DP = new int[n + 1];
        DP[0] = 1;
        int ans = recursive(DP, n);
        res.append(ans).append("\n");
    }

    private static int recursive(int[] DP, int n) {
        if (DP[n] != 0) {
            return DP[n];
        }

        int ans = 0;
        for (int i = 1; i <= n; i++) {
            ans += atomic(i) * recursive(DP, n - i);
        }

        DP[n] = ans;
        return ans;
    }

    private static int atomic(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 4;
        }

        if ((n & 1) == 1) {
            return 2;
        }
        return 3;
    }


    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
3 1 6 5 4 5 2

 */