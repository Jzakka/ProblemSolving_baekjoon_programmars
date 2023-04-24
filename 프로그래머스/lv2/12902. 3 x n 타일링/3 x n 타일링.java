class Solution {
    private long[] DP = new long[5_000];

    private final int MOD = 1_000_000_007;

    public int solution(int n) {
        if (n % 2 == 1) {
            return 0;
        }
        int num = n / 2;

        return (int) f(num);
    }

    public long f(int x) {
        if (DP[x] != 0) {
            return DP[x];
        }
        if (x == 0) {
            return 1;
        }
        long head = ((3 * f(x - 1)));
        long tail = 0;

        for (int i = 0; i < x - 1; i++) {
            tail += f(i);
        }
        tail %= MOD;
        tail = 2 * tail % MOD;

        DP[x] = (head + tail) % MOD;
        return DP[x];
    }
}