import java.io.*;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            int[] info = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int n = info[0];
            int w = info[1];


            int[] inner = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] outer = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            solution(inner, outer, w);
        }


        printRes();
    }

    private static void solution(int[] inner, int[] outer, int w) {
        int n = inner.length;

        if (n == 1) {
            res.append(inner[0] + outer[0] <= w ? 1 : 2).append("\n");
            return;
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            int[][] DP = new int[n][5];
            Arrays.stream(DP).forEach(row -> Arrays.fill(row, 1_000_000_000));

            if (i == 0) {
                DP[0][0] = 2;
            } else if (i == 1 && inner[0] + inner[n - 1] <= w) {
                DP[0][1] = 2;
            } else if (i == 2 && outer[0] + outer[n - 1] <= w) {
                DP[0][2] = 2;
            } else if (i == 3 && inner[0] + inner[n - 1] <= w && outer[0] + outer[n - 1] <= w) {
                DP[0][3] = 2;
            } else if (i == 4 && inner[0] + outer[0] <= w) {
                DP[0][4] = 1;
            }

            for (int j = 1; j < DP.length - 1; j++) {
                DP[j][0] = Arrays.stream(DP[j - 1]).min().getAsInt() + 2;

                if (inner[j] + outer[j] <= w) {
                    DP[j][4] = DP[j][0] - 1;
                }
                if (inner[j] + inner[j - 1] <= w) {
                    DP[j][1] = Math.min(DP[j - 1][0], DP[j - 1][2]) + 1;
                }
                if (outer[j] + outer[j - 1] <= w) {
                    DP[j][2] = Math.min(DP[j - 1][0], DP[j - 1][1]) + 1;
                }
                if (inner[j] + inner[j - 1] <= w && outer[j] + outer[j - 1] <= w) {
                    DP[j][3] = DP[j - 1][0];
                }
            }

            int j = n - 1;
            // 마지막 셀 처리
            if (i == 0 || i == 4) {
                DP[j][0] = Arrays.stream(DP[j - 1]).min().getAsInt() + 2;

                if (inner[j] + outer[j] <= w) {
                    DP[j][4] = DP[j][0] - 1;
                }
                if (inner[j] + inner[j - 1] <= w) {
                    DP[j][1] = Math.min(DP[j - 1][0], DP[j - 1][2]) + 1;
                }
                if (outer[j] + outer[j - 1] <= w) {
                    DP[j][2] = Math.min(DP[j - 1][0], DP[j - 1][1]) + 1;
                }
                if (inner[j] + inner[j - 1] <= w && outer[j] + outer[j - 1] <= w) {
                    DP[j][3] = DP[j - 1][0];
                }
            } else if (i == 1) {
                DP[j][0] = Arrays.stream(DP[j - 1]).min().getAsInt() + 1;
                if (outer[j] + outer[j - 1] <= w) {
                    DP[j][2] = Math.min(DP[j - 1][0], DP[j - 1][1]);
                }
            } else if (i == 2) {
                DP[j][0] = Arrays.stream(DP[j - 1]).min().getAsInt() + 1;
                if (inner[j] + inner[j - 1] <= w) {
                    DP[j][1] = Math.min(DP[j - 1][0], DP[j - 1][2]);
                }
            }else { // i == 3
                DP[j][0] = Arrays.stream(DP[n - 2]).min().getAsInt();
            }

            ans = Math.min(ans, Arrays.stream(DP[n - 1]).min().getAsInt());
        }

        res.append(ans).append("\n");
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }

    /*
    12
    20
    32
    4
    52
    60
    72
    8
    92
    104
    112
    12
    132
    140
     */
}