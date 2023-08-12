import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static final long MOD = 1_000_000_007L;

    private static long[][] identity;

    public static void main(String[] args) {
        String[] nms = sc.nextLine().split("\\s+");
        long n = Long.parseLong(nms[0]);
        int m = Integer.parseInt(nms[1]);

        int[][] works = new int[2][3];
        works[0] = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        works[1] = Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        solution(n, m, works);
    }

    private static void solution(long n, int m, int[][] works) {
        long[][] transition = new long[6 * (m + 1)][6 * (m + 1)];

        identity = new long[6 * (m + 1)][6 * (m + 1)];
        for (int i = 0; i < 6 * (m + 1); i++) {
            identity[i][i] = 1;
        }

        for (int work = 0; work < 2; work++) {
            for (int place = 0; place < 3; place++) {
                for (int progress = 0; progress < m + 1; progress++) {
                    int rowIdx = indexOf(m, work, place, progress);

                    for (int nextWork = 0; nextWork < 2; nextWork++) {
                        for (int nextPlace = 0; nextPlace < 3; nextPlace++) {
                            int delta = works[nextWork][nextPlace];

                            if (place == nextPlace) {
                                delta /= 2;
                            }

                            int result = progress + delta;
                            if (result > m) {
                                result = m;
                            }

                            int colIdx = indexOf(m, nextWork, nextPlace, result);
                            transition[rowIdx][colIdx]++;
                        }
                    }

                }
            }
        }

        long[] firstDay = new long[6 * (m + 1)];

        for (int work = 0; work < 2; work++) {
            for (int place = 0; place < 3; place++) {
                int progress = works[work][place];
                if (progress > m) {
                    progress = m;
                }
                int idx = indexOf(m, work, place, progress);
                firstDay[idx]++;
            }
        }

        long[] res = matMul(firstDay, matPow(n - 1, transition));

        long ans = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                int idx = indexOf(m, i, j, m);
                ans += res[idx];
                ans %= MOD;
            }
        }
        System.out.println(ans);
    }

    private static int indexOf(final int m, int nextWork, int nextPlace, int progress) {
        return nextWork * (3 * (m + 1)) + nextPlace * (m + 1) + progress;
    }

    private static long[][] matPow(long n, long[][] matrix) {
        if (n == 0) {
            return identity;
        }

        if ((n & 1) == 1) {
            return matMul(matrix, matPow(n - 1, matrix));
        }

        long[][] half = matPow(n / 2, matrix);
        return matMul(half, half);
    }

    private static long[][] matMul(long[][] matrix1, long[][] matrix2) {
        int m = matrix1.length;
        int n = matrix1[0].length;
        int l = matrix2[0].length;
        long[][] result = new long[n][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < l; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] = (result[i][j] + matrix1[i][k] * matrix2[k][j] % MOD) % MOD;
                }
            }
        }

        return result;
    }

    private static long[] matMul(long[] matrix1, long[][] matrix2) {
        int n = matrix1.length;
        int m = matrix2[0].length;
        long[] result = new long[m];

        for (int j = 0; j < m; j++) {
            for (int k = 0; k < n; k++) {
                result[j] = (result[j] + matrix1[k] * matrix2[k][j] % MOD) % MOD;
            }
        }

        return result;
    }

}