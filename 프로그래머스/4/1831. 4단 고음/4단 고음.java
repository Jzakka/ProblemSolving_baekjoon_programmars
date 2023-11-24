class Solution {
    public int solution(int n) {
        int maxStar = (int) Math.floor(Math.log(n) / Math.log(3));
        int maxLen = maxStar * 3;

        return count(n, maxLen, maxStar, maxStar * 2, 0, 0);
    }

    // 거꾸로 계산
    int count(final int n, int remainLen, int remainStar, int remainPlus, int usedStar, int usedPlus) {
        if (remainLen == 0) {
            return n == 1 ? 1 : 0;
        }
        int res = 0;
        if (remainStar > 0 && (usedStar +1) * 2 <= usedPlus && n %3 ==0) {
            res += count(n/3, remainLen-1, remainStar - 1, remainPlus, usedStar + 1, usedPlus);
        }
        if (remainPlus > 0) {
            res += count(n - 1, remainLen - 1, remainStar, remainPlus - 1, usedStar, usedPlus + 1);
        }
        return res;
    }

}