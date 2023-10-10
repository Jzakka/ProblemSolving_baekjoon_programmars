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

        int[][] items = new int[n][];
        for (int i = 0; i < n; i++) {
            items[i] = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }

        solution(items, m);

        printRes();
    }

    /*
    핵심 아이디어: binary lifting
    물건이 여러개인 경우 2^x 개수로 묶어서 한 개의 아이템으로 취급
    ex) 아이템이 11개인 경우 => 1개짜리, 2개짜리, 4개짜리, 4개짜리 그룹이 하나의 아이템이 될 수있음
     */
    private static void solution(int[][] items, int m) {
        List<int[]> groupedItems = new ArrayList<>();

        for (int[] item : items) {
            int weight = item[0];
            int happy = item[1];
            int count = item[2];

            int k = 0;
            int groupCount;
            while ((count - (groupCount = 1<<k))  > 0) {
                count -= groupCount;

                groupedItems.add(new int[]{groupCount * weight, groupCount * happy});
                k++;
            }

            if (count > 0) {
                groupedItems.add(new int[]{count * weight, count * happy});
            }
        }

        int[][] DP = new int[groupedItems.size() + 1][m + 1];
        for (int i = 1; i < DP.length; i++) {
            int weight = groupedItems.get(i - 1)[0];
            int happy = groupedItems.get(i - 1)[1];

            for (int j = 0; j < DP[0].length; j++) {
                if (j - weight < 0) {
                    DP[i][j] = DP[i - 1][j];
                } else {
                    DP[i][j] = Math.max(DP[i - 1][j], DP[i - 1][j - weight] + happy);
                }
            }
        }

        res.append(DP[DP.length - 1][m]);
    }


    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}