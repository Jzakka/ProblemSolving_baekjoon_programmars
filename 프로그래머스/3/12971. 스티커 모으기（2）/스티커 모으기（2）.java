import java.util.*;

class Solution {
    public int solution(int sticker[]) {
        int n = sticker.length;
        int[][] DP = new int[2][n];
        // 첫째 골랐을 때,
        DP[0][0] = sticker[0];
        for(int i=2;i<n-1;i++){
            int maxVal = Math.max((i>=3 ? DP[0][i-3] : 0), DP[0][i-2]);
            
            DP[0][i] = maxVal + sticker[i];
        }
        // 안골랐을 때,
        for(int i = 1;i<n;i++){
            int maxVal = Math.max((i>=3 ? DP[1][i-3] : 0), (i>=2 ? DP[1][i-2] : 0));
            
            DP[1][i] = maxVal + sticker[i];
        }
        
        // for(int[] row:DP){
        //     for(int num:row){
        //         System.out.print(num + " ");
        //     }
        //     System.out.println();
        // }
        
        return Arrays.stream(DP)
            .flatMapToInt(Arrays::stream)
            .max()
            .orElse(0);
    }
}