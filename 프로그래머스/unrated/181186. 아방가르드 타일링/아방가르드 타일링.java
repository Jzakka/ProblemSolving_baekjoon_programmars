class Solution {
    static final int MOD = 1_000_000_007;
    int[] DP = new int[100_001];
    long[] f;
    {
        DP[0] = DP[1] = 1;
    }
    public int solution(int n) {
        f = new long[100_001];
        f[1] = 1;
        if(n >= 2){
            f[2] = 2;
            if(n>=3){
                f[3] = 5;            
            }
        }
        for(int i = 4;i<=n;i++){
            if(i%3 == 0){
                f[i] = 4;
            }else{
                f[i] = 2;
            }
        }
        
        for(int i=2;i<=n;i++){
            long res = 0;
            for(int j = 1;j<=i;j++){
                res += f[j] * DP[i-j];
            }
            DP[i] = (int)(res%MOD);
        }
        return DP[n];
    }
}