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
        int[] shapes = getInts();

        int ans = solution(shapes);

        res.append(ans);

        bw.write(res.toString());
        bw.close();
    }

    public static int solution(int[] shapes) {
        int one = 0;
        int two = 0;
        int three = 0;
        for (int shape : shapes) {
            switch (shape) {
                case 1:
                    one++;
                    break;
                case 2:
                    two++;
                    break;
                default:
                    three++;
            }
        }

        int[] counts = {one, two, three};
        int[] cases = {
                countSwap(shapes, counts, 1, 2, 3),
                countSwap(shapes, counts, 1, 3, 2),
                countSwap(shapes, counts, 2, 1, 3),
                countSwap(shapes, counts, 2, 3, 1),
                countSwap(shapes, counts, 3, 1, 2),
                countSwap(shapes, counts, 3, 2, 1)
        };
        return Arrays.stream(cases).min().getAsInt();
    }

    private static int countSwap(int[] shapes, int[] counts, int... ordinal) {
        int[][] table = makeTable(shapes, counts[ordinal[0] - 1], counts[ordinal[1] - 1], counts[ordinal[2] - 1]);

        int[][] objectTable = getObjectTable(ordinal, new int[]{counts[ordinal[0] - 1], counts[ordinal[1] - 1], counts[ordinal[2] - 1]});

        int exchangeCount = 0;
        for (int i = 1; i < 3; i++) {
            int num = ordinal[i - 1];
            int count = counts[num - 1];

            for (int j = 1; j <= 3; j++) {
                int objectCount = j == num ? count : 0;
                if (table[i][j] < objectCount) {
                    exchangeCount += objectCount - table[i][j];
                    exchange(table, objectTable, i, j);
                }
            }
        }

        return exchangeCount;
    }

    private static int[][] getObjectTable(int[] ordinal, int[] counts) {
        int[][] objectTable = new int[4][4];

        for (int i = 0; i < ordinal.length; i++) {
            int num = ordinal[i];
            int count = counts[i];

            objectTable[i+1][num] = count;
        }

        return objectTable;
    }

    private static int[][] makeTable(int[] shapes, int area1, int area2, int area3) {
        int[][] table = new int[4][4];

        int idx = 0;

        for (int i = 0; i < area1; i++) {
            int num = shapes[idx++];
            table[1][num]++;
        }
        for (int i = 0; i < area2; i++) {
            int num = shapes[idx++];
            table[2][num]++;
        }
        for (int i = 0; i < area3; i++) {
            int num = shapes[idx++];
            table[3][num]++;
        }

        return table;
    }

    private static void exchange(int[][] table, int[][] objectTable, int x, int y) {
        for (int i = x + 1; i <= 3; i++) {
            int count = table[i][y];

            table[x][y] += count;
            table[i][y] = 0;
            for (int j = 1; j <= 3 && count > 0; j++) {
                if (j == y || table[i][j] >= objectTable[i][j]) {
                    continue;
                }
                if (table[x][j] > 0) {
                    int give = Math.min(count, table[x][j]);
                    table[i][j] += give;
                    table[x][j] -= give;
                    count -= give;
                }
            }
        }
    }
    // 1 3 1 2 1

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

/*
1
1 2 1 3 3 1 1
 */