
import java.util.*;

class Solution {
    int solution(int[][] land) {
        int[] prevLine = {0, 0, 0, 0};

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < 4; j++) {
                int maxVal = Integer.MIN_VALUE;
                for (int k = 0; k < 4; k++) {
                    if (k != j) {
                        maxVal = Math.max(maxVal, prevLine[k]);
                    }
                }
                land[i][j] += maxVal;
            }
            prevLine = land[i];
        }

        return Arrays.stream(prevLine).max().getAsInt();
    }
}