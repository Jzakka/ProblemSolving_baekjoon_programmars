import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        String[] city = new String[n];

        for (int i = 0; i < n; i++) {
            city[i] = br.readLine();
        }

        solution(n, city);

        printRes();
    }

//    private static int[] dx = {-1, 0, 0, 1};
//    private static int[] dy = {0, 1, -1, 0};

    private static void solution(int n, String[] city) {
        // DP[i][j][dir] : i행 j열에서 dir방향으로 가장 가까운 건물이 멀쩡한 건물인가?
        boolean[][][] DP = new boolean[n][n][4];

        // 서쪽에서 가장 가까운 건물
        for (int i = 0; i < n; i++) {
            boolean isUninjured = city[i].charAt(0) == 'O';
            for (int j = 1; j < n; j++) {
                if (city[i].charAt(j) == '.') {
                    DP[i][j][0] = isUninjured;
                } else {
                    isUninjured = city[i].charAt(j) == 'O';
                }
            }
        }

        // 동쪽에서 가장 가까운 건물
        for (int i = 0; i < n; i++) {
            boolean isUninjured = city[i].charAt(n-1) == 'O';
            for (int j = n-2; j >= 0; j--) {
                if (city[i].charAt(j) == '.') {
                    DP[i][j][1] = isUninjured;
                } else {
                    isUninjured = city[i].charAt(j) == 'O';
                }
            }
        }

        // 북쪽에서 가장 가까운 건물
        for (int j = 0; j < n; j++) {
            boolean isUninjured = city[0].charAt(j) == 'O';
            for (int i = 1; i < n; i++) {
                if (city[i].charAt(j) == '.') {
                    DP[i][j][2] = isUninjured;
                } else {
                    isUninjured = city[i].charAt(j) == 'O';
                }
            }
        }

        // 남쪽에서 가장 가까운 건물
        for (int j = 0; j < n; j++) {
            boolean isUninjured = city[n-1].charAt(j) == 'O';
            for (int i = n-2; i >= 0; i--) {
                if (city[i].charAt(j) == '.') {
                    DP[i][j][3] = isUninjured;
                } else {
                    isUninjured = city[i].charAt(j) == 'O';
                }
            }
        }

        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (city[i].charAt(j) == '.') {
                    if (DP[i][j][0] || DP[i][j][1] || DP[i][j][2] || DP[i][j][3]) {
                        row.append('.');
                    } else {
                        row.append('B');
                    }
                } else {
                    row.append(city[i].charAt(j));
                }
            }
            res.append(row).append("\n");
        }
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}
