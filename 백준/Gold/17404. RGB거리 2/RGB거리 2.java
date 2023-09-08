import java.io.*;
import java.util.Arrays;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] costs = new int[n][];

        for (int i = 0; i < n; i++) {
            costs[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(n, costs);
        printRes();
    }

    private static void solution(int n, int[][] costs) {
        int ans = Integer.MAX_VALUE;

        for (int firstHouseColor = 0; firstHouseColor < 3; firstHouseColor++) {
            int[][] DP = new int[n][3];
            Arrays.stream(DP).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE));

            int[] notFirstColor = otherColors(firstHouseColor);
            DP[0][firstHouseColor] = costs[0][firstHouseColor];
            DP[0][notFirstColor[0]] = Integer.MAX_VALUE / 2;
            DP[0][notFirstColor[1]] = Integer.MAX_VALUE / 2;

            for (int currentHouse = 1; currentHouse < n - 1; currentHouse++) {
                int prevHouse = currentHouse - 1;
                for (int currentHouseColor = 0; currentHouseColor <3; currentHouseColor++) {
                    int[] otherColors = otherColors(currentHouseColor);
                    DP[currentHouse][currentHouseColor] = costs[currentHouse][currentHouseColor]
                            + Math.min(DP[prevHouse][otherColors[0]], DP[prevHouse][otherColors[1]]);
                }
            }

            for (int lastHouseColor = 0; lastHouseColor < 3; lastHouseColor++) {
                if (firstHouseColor == lastHouseColor) {
                    continue;
                }
                int[] otherColors = otherColors(lastHouseColor);
                for (int otherColor : otherColors) {
                    DP[n-1][lastHouseColor] = Math.min(DP[n-1][lastHouseColor],costs[n - 1][lastHouseColor] + DP[n - 2][otherColor]);
                    ans = Math.min(DP[n-1][lastHouseColor], ans);
                }
            }
        }
        res.append(ans);
    }

    private static int[] otherColors(int color) {
        int otherColor1;
        int otherColor2;

        switch (color) {
            case RED:
                otherColor1 = BLUE;
                otherColor2 = GREEN;
                break;
            case BLUE:
                otherColor1 = RED;
                otherColor2 = GREEN;
                break;
            default:
                otherColor1 = RED;
                otherColor2 = BLUE;
        }
        return new int[]{otherColor1, otherColor2};
    }

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}