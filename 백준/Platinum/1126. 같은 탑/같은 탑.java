import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[] blocks = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        solution(blocks);

        printRes();
    }

    private static void solution(int[] blocks) {
        // DP[i][j] : i번째 블럭을 사용해서 두 탑의 높이차가 j가 되게 했을 떄 최대 높이
        int[][] DP = new int[blocks.length][500_001];
        Arrays.stream(DP).forEach(row -> Arrays.fill(row, -1));
        DP[0][0] = 0;
        DP[0][blocks[0]] = blocks[0];

        for (int i = 0; i < DP.length - 1; i++) {
            for (int j = 0; j < DP[0].length; j++) {
                int currentHeight = DP[i][j];

                if (currentHeight >= 0) {
                    int diff = Math.abs(j - blocks[i + 1]);

                    // i+1 번째 블록을 사용하지 않았다.
                    DP[i + 1][j] = Math.max(DP[i + 1][j], currentHeight);

                    // 더 높은 탑에 i+1 번째 블록을 올려놓았다.
                    if (j + blocks[i + 1] <= 500_000) {
                        DP[i + 1][j + blocks[i + 1]] = Math.max(DP[i + 1][j + blocks[i + 1]], currentHeight + blocks[i + 1]);
                    }

                    // 더 작은 탑에 i+1 번째 블록을 올려놓았다.
                    if (diff <= 500_000) {
                        DP[i + 1][diff] = Math.max(DP[i + 1][diff], currentHeight + (j < blocks[i + 1] ? diff : 0));
                    }
                }
            }
        }

        res.append(DP[DP.length - 1][0] == 0 ? -1 : DP[DP.length - 1][0]);
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}