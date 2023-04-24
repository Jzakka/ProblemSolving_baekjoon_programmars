class Solution {
    private final Integer NONE = 0;
    private final Integer IN_SAME_YAXLE = 1;
    private final Integer IN_SAME_XAXLE = 2;

    private int n;
    private int m;
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        setup(m, n);
        int[] minDistPow = new int[balls.length];
        int i = 0;

        for (int[] ball : balls) {
            int destX = ball[0];
            int destY = ball[1];

            minDistPow[i++] = getMinDistPow(startX, startY, destX, destY);
        }
        return minDistPow;
    }

    public void setup(int m, int n) {
        this.m = m;
        this.n = n;
    }

    private int getMinDistPow(int startX, int startY, int destX, int destY) {
        int situation = which(startX, startY, destX, destY);

        if (situation == IN_SAME_XAXLE) {
            return getMinDistPowInSameXAxle(startX, destX, destY);
        } else if (situation == IN_SAME_YAXLE) {
            return getMinDistPowInSameYAxle(startY, destX, destY);
        }
        return getMinDistancePower(startX, startY, destX, destY);
    }

    private int getMinDistancePower(int startX, int startY, int destX, int destY) {
        int distancePower1 = getDistancePower(startX, destX, Math.abs(startY - destY));
        int distancePower2 = getDistancePower(n-startY,n- destY, Math.abs(startX - destX));
        int distancePower3 = getDistancePower(m-startX, m-destX, Math.abs(startY - destY));
        int distancePower4 = getDistancePower(startY, destY, Math.abs(startX - destX));

        return Math.min(Math.min(distancePower1, distancePower2), Math.min(distancePower3, distancePower4));
    }

    private int getMinDistPowInSameYAxle(int startY, int destX, int destY) {
        int _2a = Math.abs(startY - destY);
        int w = Math.min(m - destX, destX);

        int verticalPow;
        if (startY < destY) {
            verticalPow = (startY + destY) * (startY + destY);
        } else {
            verticalPow = (2 * n - startY - destY) * (2 * n - startY - destY);
        }

        return Math.min(verticalPow, _2a * _2a + 4 * w * w);
    }

    private int getMinDistPowInSameXAxle(int startX, int destX, int destY) {
        int _2w = Math.abs(startX - destX);
        int h = Math.min(n - destY, destY);

        int horizonPow;
        if (startX < destX) {
            horizonPow = (startX + destX) * (startX + destX);
        } else {
            horizonPow = (2 * m - startX - destX) * (2 * m - startX - destX);
        }

        return Math.min(horizonPow, _2w * _2w + 4 * h * h);
    }

    public int getDistancePower(int base1, int base2, int diff) {
        return diff * diff + (base1 + base2) * (base1 + base2);
    }

    public int which(int startX, int startY, int destX, int destY) {
        if (startX == destX) {
            return IN_SAME_YAXLE;
        }
        if (startY == destY) {
            return IN_SAME_XAXLE;
        }

        return NONE;
    }
}