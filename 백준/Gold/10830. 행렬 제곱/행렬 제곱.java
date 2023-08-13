import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static final int MOD = 1_000;

    public static void main(String[] args) {
        String[] nms = sc.nextLine().split("\\s+");
        int n = Integer.parseInt(nms[0]);
        long b = Long.parseLong(nms[1]);

        int[][] mat = new int[n][];
        for (int i = 0; i < n; i++) {
            mat[i] = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        }

        solution(mat, b);
    }

    private static void solution(int[][] mat, long b) {
        int[][] identity = new int[mat.length][mat.length];
        for (int i = 0; i < identity.length; i++) {
            identity[i][i] = 1;
        }

        int[][] res = matPow(identity, mat, b);

        print(res);
    }

    private static void print(int[][] res) {
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[][] matPow(final int[][] identity, int[][] mat, long b) {
        if (b == 0) {
            return identity;
        }

        if ((b & 1) == 1) {
            return matMul(mat, matPow(identity, mat, b - 1));
        }

        int[][] half = matPow(identity, mat, b / 2);
        return matMul(half, half);
    }

    private static int[][] matMul(int[][] mat1, int[][] mat2) {
        int m = mat1.length;
        int l = mat1[0].length;
        int n = mat2[0].length;

        int[][] res = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < l; k++) {
                    res[i][j] += mat1[i][k] * mat2[k][j] % MOD;
                    res[i][j] %= MOD;
                }
            }
        }

        return res;
    }
}