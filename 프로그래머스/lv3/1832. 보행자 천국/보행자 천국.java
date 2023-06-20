class Solution {
    int MOD = 20170805;
    final int UP = 0;
    final int LEFT = 1;
    long[][] DP;
    int[][] cityMap;
    
    public int solution(int m, int n, int[][] cityMap) {
        DP = new long[m][n];
        this.cityMap = cityMap;
        
        int cases = 1;
        for(int i=0;i<m;i++){
            if(cityMap[i][0] == 1){
                cases = 0;
            }
            DP[i][0] = cases;
        }
        
        cases = 1;
        for(int i=0;i<n;i++){
            if(cityMap[0][i] == 1){
                cases = 0;
            }
            DP[0][i] = cases;
        }
        
        for(int i=1;i<m;i++){
            for(int j = 1;j<n;j++){
                if(cityMap[i][j] != 1){
                    // System.out.println("["+i+","+j+"]");
                    
                    DP[i][j] = over(i-1,j, UP) + over(i,j-1, LEFT);
                    DP[i][j] %= MOD;
                    
                    // System.out.println();
                }
            }
        }
        
        return (int)DP[m-1][n-1];
    }
    
    long over(int x, int y, int dir){
        if(dir == UP){
            while(x >= 0 && cityMap[x][y] == 2){
                x--;
            }
        }else{
            while(y >= 0 && cityMap[x][y] == 2){
                y--;
            }
        }
        
        if(x < 0 || y < 0){
            return 0;
        }
        
        // System.out.println("\t["+x+","+y+"]");
        return DP[x][y];
    }
}

/*

0 2 0 0 0 2
0 0 2 0 1 0
1 0 0 2 2 0

DP[i][j] = f(DP[i-1][j], up) + f(DP[i][j-1], left)

DP(pos, dir)는 pos 가 2이면 dir 방향으로 2가 아닐때까지 pos를 움직이고 그값을 반환

*/