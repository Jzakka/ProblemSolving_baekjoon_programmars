class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] DP = new int[n][n];
        DP[0][0] = triangle[0][0];
    
        for(int i=1;i<n;i++){
            DP[i][0] = DP[i-1][0] + triangle[i][0];
            for(int j=1;j<=i;j++){
                DP[i][j] = Math.max(DP[i-1][j-1], DP[i-1][j]) + triangle[i][j];
            }
            DP[i][i] = DP[i-1][i-1] + triangle[i][i];
        }
        
        // for(int i=0;i<n;i++){
        //     for(int j = 0;j<n;j++){
        //         System.out.print(DP[i][j] + " ");
        //     }
        //         System.out.println();
        // }
        
        int ans = 0;
        for(int i=0;i<n;i++){
            ans = Math.max(ans, DP[n-1][i]);
        }
        return ans;
    }
}