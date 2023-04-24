class Solution {
    public long solution(int n) {
        if (n <= 2) {
            return n;
        }
        long prev = 2;
        long prePrev = 1;
        long now = 0;
        for (int i = 3; i <= n; i++) {
            now = (prev + prePrev) % 1234567;
            prePrev = prev;
            prev = now;
        }

        return now;
    }
}