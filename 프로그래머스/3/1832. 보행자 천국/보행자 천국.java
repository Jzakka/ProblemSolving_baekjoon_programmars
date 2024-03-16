import static java.lang.System.*;

class Solution {
    int MOD = 20170805;
    final int LEFT = 0;
    final int UP = 1;
    int[][][] DP;
    public int solution(int m, int n, int[][] cityMap) {
        //DP[i][j][d] : (i,j)를 d방향에서 진입하는 경우의 수
        DP = new int[m][n][2];
        
        for(int i=0;i<n && cityMap[0][i] != 1;i++){
            DP[0][i][LEFT] = 1;
        }
        for(int i=0;i<m && cityMap[i][0] != 1;i++){
            DP[i][0][UP] = 1;
        }
        
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(cityMap[i][j] == 1) { continue; }
                
                DP[i][j][UP] = (cityMap[i-1][j] == 2) ? DP[i-1][j][UP] : sum(i-1, j);
                DP[i][j][LEFT] = (cityMap[i][j-1] == 2) ? DP[i][j-1][LEFT] : sum(i, j-1);          
            }
        }
        
        return sum(m-1, n-1);
    }
    
    int sum(int x,int y){
        return (DP[x][y][0] + DP[x][y][1]) % MOD;
    }
}