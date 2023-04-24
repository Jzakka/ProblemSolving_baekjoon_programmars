import java.util.Arrays;

class Solution {
    private int[] apeach;

    private int diff = -1;

    private int[] lion = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private final int[] CANNOT_WIN = {-1};

    public int[] solution(int n, int[] apeach) {
        this.apeach = apeach;
        recursive(lion, 0, n);
        return diff != -1 ? lion : CANNOT_WIN;
    }

    public void recursive(int[] lion, int idx, int n) {
        if (idx == lion.length || n <= 0) {
            int diff;
            if ((diff = diff(apeach, lion)) > 0) {
                lion[10] += Math.max(n, 0);
                if (diff > this.diff) {
                    this.lion = Arrays.copyOf(lion, lion.length);
                    this.diff = diff;
                } else if (diff == this.diff && compare(lion)) {
                    this.lion = Arrays.copyOf(lion, lion.length);
                }
                lion[10] -= Math.max(n, 0);
            }
            return;
        }
        int apeachHit = apeach[idx];
        // more
        if (n > apeachHit) {
            lion[idx] = apeachHit + 1;
            recursive(lion, idx + 1, n - lion[idx]);
            lion[idx] = 0;
        }
        // less
        recursive(lion, idx + 1, n);
    }

    private boolean compare(int[] lion) {
        int i=10;
        while (lion[i] == this.lion[i]) {
            i--;
        }

        return lion[i] > this.lion[i];
    }

    public int diff(int[] apeach, int[] lion) {
        int lionScore = 0;
        int apeachScore = 0;
        for (int i = 0; i <= 10; i++) {
            int apeachHit = apeach[i];
            int lionHit = lion[i];

            if (apeachHit >= lionHit && !(apeachHit == 0 && lionHit == 0)) {
                apeachScore += 10 - i;
            } else if (apeachHit < lionHit) {
                lionScore += 10 - i;
            }
        }

        return lionScore - apeachScore;
    }
}