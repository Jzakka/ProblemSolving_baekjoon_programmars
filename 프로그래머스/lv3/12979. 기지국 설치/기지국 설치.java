class Solution {
    public int solution(int n, int[] stations, int w) {

        int start = 1;

        int res = 0;
        int len = 2 * w + 1;
        for (int station : stations) {
            int left = station - w;
            int right = station + w;

            if (start < left) {
                int spaceLen = left - start;
                int q = spaceLen / len;
                if (q * len < spaceLen) {
                    q++;
                }
                res += q;
            }
            start = right + 1;
        }

        if (start > n) {
            return res;
        } else {
            int spaceLen = n - start + 1;
            int q = spaceLen / len;
            if (q * len < spaceLen) {
                q++;
            }
            res += q;
        }

        return res;
    }
}