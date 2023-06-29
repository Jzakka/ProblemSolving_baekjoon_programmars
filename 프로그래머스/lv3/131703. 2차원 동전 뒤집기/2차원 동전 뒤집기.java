class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int n = target.length;
        int m = target[0].length;
        
        int [][] xor1 = new int[n][m];
        int [][] xor2 = new int[n][m];
        
        for(int i = 0;i<target.length;i++){
            for(int j = 0;j<target[0].length;j++){
                xor1[i][j] = xor2[i][j] = beginning[i][j] ^ target[i][j];
            }
        }
        
        int cnt1 = 0;
        int cnt2 = 0;
        for(int i=0;i<n;i++){
            if(xor1[i][0] == 1){
                for(int j = 0;j<m;j++){
                    xor1[i][j] ^= 1;
                }
                cnt1++;
            }else{
                cnt2++;
            }
        }
        for(int j = 0;j<m;j++){
            if(xor1[0][j] == 1){
                for(int i = 0;i<n;i++){
                    xor1[i][j] ^= 1;
                }
                cnt1++;
            }else{
                cnt2++;
            }
        }
        
        int res = 0;
        for(int i = 0;i<target.length;i++){
            for(int j = 0;j<target[0].length;j++){
                res |= xor1[i][j];
            }
        }
        
        // return cnt;
        // return -1;
        return res == 0 ? Math.min(cnt1,cnt2) : -1;
    }
}