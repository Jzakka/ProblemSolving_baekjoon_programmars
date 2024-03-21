import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    int[][] DP;
    int[] P;
    public int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length;
        P = new int[n+1];
        DP = new int[n+1][n+1];
        
        P[0] = matrix_sizes[0][0];
        for(int i=1;i<P.length;i++){
            P[i] = matrix_sizes[i-1][1];
        }
        
        for(int j=2;j<DP.length;j++){
            for(int i=1;i<DP.length && j + i - 1 < DP.length;i++){
                DP[i][j + i - 1] = calc(i, j + i - 1);
            }
        }
        
        return DP[1][n];
    }
    
    int calc(int i, int j){
        int res = Integer.MAX_VALUE;
        for(int k = i;k<j;k++){
            res = Math.min(res, (DP[i][k] + DP[k+1][j] + P[i-1]*P[k]*P[j]));
        }
        return res;
    }
}