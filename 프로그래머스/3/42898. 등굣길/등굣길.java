import java.util.*;
import java.util.stream.*;

class Solution {
    final int MOD = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] DP = new int[n][m];
        
        for(int i=0;i<m;i++){
            if(contains(puddles, 0, i)){
                break;
            }
            DP[0][i] = 1;
        }
        for(int i=0;i<n;i++){
            if(contains(puddles, i, 0)){
                break;
            }
            DP[i][0] = 1;
        }
        
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                if(contains(puddles, i,j)){
                    continue;
                }
                DP[i][j] = (DP[i-1][j] + DP[i][j-1])%MOD;
            }
        }
        // debug(DP);
        
        return DP[n-1][m-1];
    }
    
    boolean contains(int[][] puddles, int x, int y){
        for(int[] puddle : puddles){
            if(puddle[0] - 1== y && puddle[1] - 1== x){
                return true;
            }
        }
        return false;
    }
    
    void debug(int[][] DP){
        for(int i=0;i<DP.length;i++){
            for(int j=0;j<DP[0].length;j++){
                System.out.print(DP[i][j] + " ");
            }
            System.out.println();
        }
    }
}