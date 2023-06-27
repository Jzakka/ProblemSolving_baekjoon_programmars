class Solution {
    public int solution(int[][] matrix_sizes) {
        int[] P = new int[matrix_sizes.length+1];
        P[0] = matrix_sizes[0][0];
        for(int i=0;i<matrix_sizes.length;i++){
            P[i+1] = matrix_sizes[i][1];
        }
        
//         for(int p:P){
//             System.out.print(p + ", ");
//         }
//         System.out.println();
        
        int[][] DP = new int[matrix_sizes.length+1][matrix_sizes.length+1];
        
        // System.out.println("DPLEN:" + DP.length);
        
        
        for(int i=1;i<DP.length;i++){
            for(int j = 1;j+i<DP.length;j++){
                // System.out.printf("i=%d, j=%d%n", i,j);
                // System.out.printf("(%d,%d)%n", j, j+i);
                DP[j][j+i] = getMin(DP, P, j, j+i);
            }
            
            // for(int x = 1;x<DP.length;x++){
            //     for(int y = 1;y < DP.length;y++){
            //         System.out.printf("%04d|", DP[x][y]);
            //     }
            //     System.out.println();
            // }
        }
        
        
        
        return DP[1][DP.length-1];
    }
    
    int getMin(int[][] DP, int[] P, int i, int j){
        // System.out.println("CALC, (" + i + ","+ j+")");
        int minVal = Integer.MAX_VALUE;
        for(int k = i; k<j;k++){
            // System.out.printf("DP[%d][%d] = %d%n", i, k, DP[i][k]);
            // System.out.printf("DP[%d][%d] = %d%n", k+1, j, DP[k+1][j]);
            // System.out.printf("P[%d] * P[%d] * P[%d] = %d%n", i-1, k, j, P[i-1] * P[k] * P[j]);
            
            int val = DP[i][k] + DP[k+1][j] + P[i-1] * P[k] * P[j];
            // System.out.println("VAL:" + val);
            minVal = Math.min(minVal, val);
        }
        return minVal;
    }
}

//p(i-1)p(k)p(j)