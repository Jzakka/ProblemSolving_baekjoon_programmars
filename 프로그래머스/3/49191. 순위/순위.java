import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    int[][] mat;
    public int solution(int n, int[][] results) {
        mat = new int[n+1][n+1];
        
        for(int[] result : results){
            int src = result[0];
            int dest = result[1];
            mat[src][dest] = 1;
            mat[dest][src] = -1;
        }
        
        // debug();
        
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(mat[j][i] == 1){
                    for(int k=1;k<=n;k++){
                        if(mat[i][k] == 1){
                            mat[j][k] = 1;
                            mat[k][j] = -1;
                            // debug();
                        }
                    }
                }
            }
        }
        
        // debug();
        
        return (int)Arrays.stream(mat)
            .filter(row -> Arrays.stream(row).filter(i->i!=0).count() == n -1)
            .count();
    }
    
    void debug(){
        out.println("==DEBUG==");
        for(int[] row: mat){
            for(int e:row){
                out.print(e + " ");
            }
            out.println();
        }
    }
}