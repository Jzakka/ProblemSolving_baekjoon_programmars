import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    public static void main(String[] args) throws Exception {
        int[] info = Arrays.stream(br.readLine().split("\\s+")).mapToInt(Integer::parseInt)
                .toArray();
        int n = info[0];
        int s = info[1];

        int[][] pics = new int[n][];
        for (int i = 0; i < n; i++) {
            pics[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(pics, s);

        printRes();
    }

    /*
    3 3
    1 10
    3 5
    4 4
     */
    private static void solution(int[][] pics, int s) {
        pics = Arrays.stream(pics)
                .filter(pic -> pic[0] >= s)
                .sorted(Comparator.comparingInt(pic -> pic[0]))
                .toArray(int[][]::new);

        // DP[i] : i 번째 그림까지 전시가능 할 떄, 최대 가격합
        int[] DP = new int[pics.length];
        DP[0] = pics[0][1];

        for (int i = 1; i < DP.length; i++) {
            int maxHeight = pics[i][0] - s;

            // maxHeight보다 작거나 같은 것을 [0,i) 범위에서 구하기
            int pos = binarySearch(pics, i, maxHeight);

            if (pos >= 0) {
                DP[i] = Math.max(DP[i - 1], DP[pos] + pics[i][1]);
            } else {
                DP[i] = Math.max(DP[i - 1], pics[i][1]);
            }
        }

        res.append(DP[DP.length - 1]);
    }

    // [0,end) 범위에서 높이가 target보다 작거나 같은 최대의 높이의 인덱스를 구하기
    private static int binarySearch(int[][] pics, int end, int target) {
        int lo = 0;
        int hi = end - 1;

        int result = -1;
        // 0 1  2  3  4  5
        // 8 8
        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (pics[mid][0] <= target) {
                result = mid;
                lo = mid + 1;
            } else if (pics[mid][0] > target) {
                hi = mid - 1;
            }
        }

        return result;
    }

    // [8]

    static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*

00001
0001
001
01
10
01

 */