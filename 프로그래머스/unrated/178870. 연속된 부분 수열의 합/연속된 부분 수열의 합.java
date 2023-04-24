class Solution {
    public int[] solution(int[] sequence, int k) {
        int s, e, sum;
        s = e = sum = 0;
        int[] res = {0, 2_000_000};
        for (; e < sequence.length; e++) {
            sum += sequence[e];
            if (sum >= k) {
                while (sum > k) {
                    sum -= sequence[s++];
                }
                if (sum == k && res[1]-res[0] > e-s) {
                    res[0] = s;
                    res[1] = e;
                }
            }
        }
        return res;
    }
}