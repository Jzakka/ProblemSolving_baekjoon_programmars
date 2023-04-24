import java.util.*;

class Solution {
    List<Double> area = new ArrayList<>();

    public double[] solution(int k, int[][] ranges) {
        area.add(0D);
        collatz(k);

        double[] result = new double[ranges.length];

        for (int i = 0; i < ranges.length; i++) {
            int[] range = ranges[i];

            if (range[0] > area.size() - 1 + range[1]) {
                result[i] = -1;
            } else {
                result[i] = area.get(area.size() - 1 + range[1]) - area.get(range[0]);
            }
        }

        return result;
    }

    public int collatz(double k) {
        int cnt = 0;
        while (k != 1) {
            double prevHeight = k;

            if ((k % 2) == 0) {
                k /= 2;
            } else {
                k *= 3;
                k++;
            }
            area.add(area.get(area.size() - 1) + (k + prevHeight) / 2);

            cnt++;
        }
        return cnt;
    }
}