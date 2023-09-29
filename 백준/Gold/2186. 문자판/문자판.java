import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();
    private static int[] dx = {-1, 0, 0, 1};
    private static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = info[0];
        int m = info[1];
        int k = info[2];

        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = br.readLine();
        }

        String word = br.readLine();

        solution(strings, k, word);

        printRes();
    }

    private static void solution(String[] strings, int k, String word) {
        int n = strings.length;
        int m = strings[0].length();
        int w = word.length();

        // DP[i][j][l] : i행j열의 문자가 단어의 l번째 문자와 일치할 때, 경우의 수
        int[][][] DP = new int[n][m][w];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (strings[i].charAt(j) == word.charAt(0)) {
                    DP[i][j][0] = 1;
                }
            }
        }

        for (int l = 1; l < w; l++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (strings[i].charAt(j) == word.charAt(l)) {
                        // 동서남북으로 k개의 좌표에서 값을 더해오기
                        for (int o = 0; o < 4; o++) {
                            int nextX = i + dx[o];
                            int nextY = j + dy[o];
                            int len = 1;

                            while (len <= k && available(n, m, nextX, nextY)) {
                                DP[i][j][l] += DP[nextX][nextY][l - 1];

                                len++;
                                nextX = i + len * dx[o];
                                nextY = j + len * dy[o];
                            }
                        }
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                ans += DP[i][j][w - 1];
            }
        }

        res.append(ans);
    }

    private static boolean available(int n, int m, int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
3 1 6 5 4 5 2

 */