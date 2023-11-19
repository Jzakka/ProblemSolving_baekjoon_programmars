import java.util.*;

class Solution {
    public int solution(int n, int count) {
        final int MOD = 1_000_000_007;
        long[][] DP = new long[n + 1][count + 1];
        
        DP[0][0] = 1;
        
        for(int i = 1;i<=n;i++){
            for(int j=1;j<=count;j++){
                DP[i][j] += DP[i-1][j-1];
                DP[i][j] %= MOD;
                DP[i][j] += (DP[i-1][j] * 2 * (i - 1)) % MOD;
                DP[i][j] %= MOD;
            }
        }
        
        return (int)DP[n][count];
    }
}