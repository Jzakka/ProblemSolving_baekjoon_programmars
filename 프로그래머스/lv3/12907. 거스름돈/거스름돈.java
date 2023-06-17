import java.util.*;

class Solution {
    int[] money;
    Map<Long, Long> DP = new HashMap<>();
    private final int MOD = 1_000_000_007;

    public int solution(int n, int[] money) {
        this.money = Arrays.stream(money).sorted().toArray();

        long res = 0;
        int moneyIdx = money.length - 1;
        int lastCoin = money[moneyIdx];
        int count = 0;
        while (lastCoin * count <= n) {
            if (lastCoin * count == n) {
                res++;
            } else {
                res += calc(n - lastCoin * count, moneyIdx - 1);
                res %= MOD;
            }
            count++;
        }
        return (int) res;
    }

    private long calc(int remain, int idx) {
        long key = cantorPair(remain, idx);
        if (DP.containsKey(key)) {
            return DP.get(key);
        }

        if (idx < 0) {
            return 0;
        }

        int coin = money[idx];
        int count = 0;
        long res = 0;
        while (coin * count <= remain) {
            if (coin * count == remain) {
                res++;
            } else {
                res += calc(remain - coin * count, idx - 1);
                res %= MOD;
            }
            count++;
        }
        DP.put(key, res);
        return res;
    }

    private long cantorPair(long a, long b) {
        return (a + b) * (a + b + 1) / 2 + b;
    }
}
