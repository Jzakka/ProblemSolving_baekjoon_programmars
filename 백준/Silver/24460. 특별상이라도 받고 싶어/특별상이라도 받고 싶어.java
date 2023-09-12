import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder res = new StringBuilder();

    private static final int MOD = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        int n = Integer.parseInt(br.readLine());

        int[][] matrix = new int[n][];

        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(br.readLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        solution(matrix);

        printRes();
    }

    private static void solution(int[][] matrix) {
        int[] pos = recursive(matrix, 0,0, matrix.length, matrix.length);
        res.append(matrix[pos[0]][pos[1]]);
    }

    private static int[] recursive(int[][] matrix, int x1, int y1, int x2, int y2) {
        if (x2 - x1 == 1 && y2 - y1 == 1) {
            return new int[]{x1, y1};
        }

        int[][] nums = new int[4][];

        nums[0] = recursive(matrix, x1, y1, (x1 + x2)/2, (y1 + y2)/2);
        nums[1] = recursive(matrix, x1, (y1 + y2)/2, (x1 + x2)/2, y2);
        nums[2] = recursive(matrix, (x1 + x2)/2, y1, x2, (y1 + y2)/2);
        nums[3] = recursive(matrix, (x1 + x2) / 2, (y1 + y2) / 2, x2, y2);

        Arrays.sort(nums, Comparator.comparingInt(arr -> matrix[arr[0]][arr[1]]));
        return nums[1];
    }

    //  0 1 2
    //  3 4 5
    //  6 7 8

    public static void printRes() throws IOException {
        bw.write(res.toString());
        bw.close();
    }
}

/*
n=10
9876543210

n=11
89876543210
98765432101
10123456789

n=12
989876543210
789876543210
898765432101

898765432101
987654321010
987654321012

210123456789
010123456789
101234567898

n=13
8989876543210
9898765432101

8789876543210
6789876543210
7898765432101

9[]0 => 8[]0, 9[]1
b[]0 => a[]0, c[]0, b[]1
0[]b => 1[]b, 1[]
 */