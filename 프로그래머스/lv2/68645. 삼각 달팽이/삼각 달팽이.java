import java.util.stream.IntStream;

class Solution {
    private int[] triangle;
    int initSwDelta = 2;
    int initNwDelta;
    int curDir = 0;
    int delta = 1;

    public int[] solution(int n) {
        triangle = new int[IntStream.range(1, n + 1).sum()];
        initNwDelta = -n;

        int idx = 0;

        for (int i = 1; i <= triangle.length; i++) {
            triangle[idx] = i;
            idx = nextIdx(idx);
        }

        return triangle;
    }

    public int nextIdx(int curIdx) {
        if (0 <= curIdx + delta && curIdx + delta < triangle.length && triangle[curIdx + delta] == 0) {
            int nextIdx = curIdx + delta;
            if (curDir != 1) {
                delta++;
            }
            return nextIdx;
        }
        if (curDir == 0) {
            curDir = 1;
            delta = 1;
            return curIdx + delta;
        } else if (curDir == 1) {
            curDir = 2;
            delta = initNwDelta++;
            return curIdx + delta++;
        } else if (curDir == 2) {
            curDir = 0;
            delta = initSwDelta;
            initSwDelta += 2;
            return curIdx + delta++;
        }
        return -1;
    }
}