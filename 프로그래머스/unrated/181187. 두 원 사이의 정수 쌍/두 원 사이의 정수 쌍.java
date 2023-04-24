class Solution {
    public long solution(int r1, int r2) {
        return quarter(r1, r2) * 4;
    }

    public double height(long radius, long x) {
        return Math.sqrt(radius * radius - x * x);
    }

    public long quarter(int r1, int r2) {
        long cnt = 0;
        for (int i = 1; i <= r1; i++) {
            cnt += Math.floor(height(r2, i)) - Math.ceil(height(r1, i))+1;
        }
        for (int i = r1 + 1; i <= r2; i++) {
            cnt += Math.floor(height(r2, i)) + 1;
        }
        return cnt;
    }
}