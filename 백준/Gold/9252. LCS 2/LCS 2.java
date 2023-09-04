import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Collectors;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        String str1 = br.readLine();
        String str2 = br.readLine();

        solution(str1, str2);


        printRes();
    }

    private static void solution(String str1, String str2) {
        int[][] DP = new int[str1.length() + 1][str2.length() + 1];
        int[][][] trace = new int[str1.length() + 1][str2.length() + 1][2];

        for (int i = 0; i <= str1.length(); i++) {
            DP[i][0] = 0;
        }

        for (int i = 0; i <= str2.length(); i++) {
            DP[0][i] = 0;
        }

        for (int i = 1; i <= str1.length(); i++) {
            char letter1 = str1.charAt(i - 1);
            for (int j = 1; j <= str2.length(); j++) {
                char letter2 = str2.charAt(j - 1);

                if (letter1 == letter2) {
                    DP[i][j] = DP[i-1][j-1] + 1;
                    trace[i][j][0] = i - 1;
                    trace[i][j][1] = j - 1;
                } else {
                    DP[i][j] = Math.max(DP[i - 1][j - 1], Math.max(DP[i - 1][j], DP[i][j - 1]));

                    if (DP[i][j] == DP[i - 1][j - 1]) {
                        trace[i][j][0] = i - 1;
                        trace[i][j][1] = j - 1;
                    } else if (DP[i][j] == DP[i - 1][j]) {
                        trace[i][j][0] = i - 1;
                        trace[i][j][1] = j;
                    } else {
                        trace[i][j][0] = i;
                        trace[i][j][1] = j - 1;
                    }
                }
            }
        }

        int length = DP[str1.length()][str2.length()];
        res.append(length).append("\n");
        if (length > 0) {
            traceBack(str1.length() + 1, str2.length() + 1, trace, str1, str2);
        }
    }

    private static void traceBack(int n, int m, int[][][] trace, String str1, String str2) {
        Deque<Character> dq = new ArrayDeque<>();

        int x = n - 1;
        int y = m - 1;

        while (x != 0 && y != 0) {
            if (str1.charAt(x-1) == str2.charAt(y-1)) {
                dq.offerFirst(str1.charAt(x-1));
            }
            int nextX = trace[x][y][0];
            int nextY = trace[x][y][1];

            x = nextX;
            y = nextY;
        }

        res.append(dq.stream().map(c -> Character.toString(c)).collect(Collectors.joining()));
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}