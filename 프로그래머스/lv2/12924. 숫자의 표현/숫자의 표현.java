class Solution {
    public int solution(int n) {
        int count = 0;
        for (int m = 1; m <= n; m++) {
            int diff = n - m * (m + 1) / 2;
            if (diff < 0) {
                break;
            }
            if (diff %m ==0) {
                count++;
            }
        }
        return count;
    }
}