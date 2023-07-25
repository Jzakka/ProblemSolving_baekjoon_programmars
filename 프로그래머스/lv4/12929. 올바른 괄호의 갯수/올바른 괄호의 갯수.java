class Solution {
    int[] DP = new int[15];
    {
        DP[0] = DP[1] = 1;
    }
    
    public int solution(int n) {
        if(DP[n] != 0){
            return DP[n];
        }
        
        int res = 0;
        for(int i=1;i<=n;i++){
            res += combMul(n-i, level(i));
        }
        return res;
    }
    
    int combMul(int n, int mul){
        if(n==0){
            return mul;
        }
        
        int res = 0;
        for(int i=1;i<=n;i++){
            res += combMul(n-i, level(i));
        }
        return res*mul;
    }
    
    int level(int n){
        return solution(n-1);
    }
}