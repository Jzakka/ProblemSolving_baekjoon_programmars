import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] size = Arrays.stream(br.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = size[0];
        int m = size[1];
        int k = size[2];

        int[][] garden = new int[n][];

        for (int i = 0; i < n; i++) {
            garden[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(garden, k);

        printRes();
    }

    private static void solution(int[][] garden, int k) {
        int oneCnt = 0;
        int n = garden.length;
        int m = garden[0].length;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                oneCnt += garden[i][j];
            }
        }

        if (oneCnt >= 2 * k) {
            res.append(0);
            return;
        }

        // row: {i, s, e} -> i행에서 [s,e] 열이 연속으로 1이라는 것을 의미
        int[] row = {-1,-1,-1};
        int[] col = {-1,-1,-1};

        for (int i = 0; i < n; i++) {
            int j = 0;
            while ( j < m && garden[i][j] == 0 ) {
                j++;
            }

            int start = j;
            int end = j;
            while (j < m && garden[i][j] == 1) {
                j++;
            }
            end = j - 1;

            if (start < m) {
                int rowOneCnt = end - start + 1;
                if (rowOneCnt == k) {
                    row[0] = i;
                    row[1] = start;
                    row[2] = end;
                    break;
                } else if (rowOneCnt >= k) {
                    res.append(start - end - 1 + 2 * k).append("\n");
                    for (int l = end + 1 - k; l <= start + k - 1; l++) {
                        res.append(i + 1).append(" ").append(l + 1).append("\n");
                    }
                    return;
                }
            }
        }

        for (int j = 0; j < m; j++) {
            int i = 0;
            while (i < n && garden[i][j] == 0) {
                i++;
            }

            int start = i;
            int end = i;
            while (i < n && garden[i][j] == 1 ) {
                i++;
            }
            end = i - 1;

            if (start < n) {
                int colOneCnt = end - start + 1;
                if (colOneCnt == k) {
                    col[0] = j;
                    col[1] = start;
                    col[2] = end;
                    break;
                } else if (colOneCnt >= k) {
                    res.append(start - end - 1 + 2 * k).append("\n");
                    for (int l = end + 1 - k; l <= start + k - 1; l++) {
                        res
                                .append(l + 1)
                                .append(" ")
                                .append(i + 1)
                                .append("\n");
                    }
                    return;
                }
            }
        }

        if (row[0] == -1) {
            res.append(col[2] - col[1] + 1).append("\n");
            for (int i = col[1]; i <= col[2]; i++) {
                res.append(i + 1).append(" ").append(col[0] + 1).append("\n");
            }
            return;
        }
        if (col[0] == -1) {
            res.append(row[2] - row[1] + 1).append("\n");
            for (int i = row[1]; i <= row[2]; i++) {
                res.append(row[0] + 1).append(" ").append(i + 1).append("\n");
            }
            return;
        }

        res.append(1).append("\n")
                .append(row[0] + 1).append(" ").append(col[0] + 1);
    }


    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}
