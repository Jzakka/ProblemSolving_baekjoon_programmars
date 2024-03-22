import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    final int MOD = 1_000_000_007;
    public int solution(int n) {
        long[] pure = new long[100_001];
        long[] DP = new long[100_001];
        DP[0] = 1;
        Arrays.fill(pure, 2);
        pure[0] = pure[1] = 1;
        pure[3] = 5;
        for(int i=6;i<=n;i+=3){
            pure[i] = 4;
        }
        
        for(int i=1;i<=n;i++){
            for(int j = 1;j<=i;j++){
                DP[i] += (DP[i - j] * pure[j]);
            }
            DP[i] %= MOD;
        }
        
        return (int)DP[n];
    }
}