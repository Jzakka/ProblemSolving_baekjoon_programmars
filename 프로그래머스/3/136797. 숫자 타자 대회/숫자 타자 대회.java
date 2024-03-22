import static java.lang.System.*;

class Solution {
    int n;
    int[][][] DP;
    int[][] move = {
      // 0 1 2 3 4 5 6 7 8 9  
        {1,7,6,7,5,4,5,3,2,3}, // 0 ->
        {7,1,2,4,2,3,5,4,5,6}, // 1 ->
        {6,2,1,2,3,2,3,5,4,5}, // 2 ->
        {7,4,2,1,5,3,2,6,5,4}, // 3 ->
        {5,2,3,5,1,2,4,2,3,5}, // 4 ->
        {4,3,2,3,2,1,2,3,2,3}, // 5 ->
        {5,5,3,2,4,2,1,5,3,2}, // 6 ->
        {3,4,5,6,2,3,5,1,2,4}, // 7 ->
        {2,5,4,5,3,2,3,2,1,2}, // 8 ->
        {3,6,5,4,5,3,2,4,2,1}, // 9 ->
    };
    
    public int solution(String numbers) {
        n = numbers.length();
        setup();
        
        fill(numbers);
        
        int ans = Integer.MAX_VALUE;
        int lastNum = numbers.charAt(n-1) - '0';
        for(int i=0;i<10;i++){
            // out.printf("[%d][%d]=%d, [%d][%d]=%d%n", lastNum, i, DP[n][lastNum][i], i, lastNum, DP[n][i][lastNum]);
            
            ans = Math.min(ans, Math.min(DP[n][lastNum][i], DP[n][i][lastNum]));
        }
        return ans;
    }
    
    void setup(){
        DP = new int[n + 1][10][10];
        
        for(int i=0;i<=n;i++){
            for(int j = 0;j<10;j++){
                for(int l = 0;l<10;l++){
                    DP[i][j][l]=Integer.MAX_VALUE;
                }
            }
        }
        
        DP[0][4][6] = 0;
    }
    
    void fill(String numbers){
        for(int i=0;i<n;i++){
            for(int l = 0;l<10;l++){
                for(int r = 0;r<10;r++){
                    if(DP[i][l][r] != Integer.MAX_VALUE){
                        move(numbers, i, l, r);
                    }
                }
            }
        }
    }
    
    void move(String numbers, int i,int l, int r){
        int nextNum = numbers.charAt(i) - '0';
        
        // out.printf("nextNum=%d, l=%d, r=%d, shortest=%d%n", nextNum,l,r, DP[i][l][r]);
        
        // 왼손 움직이기
        if(nextNum != r){
            DP[i+1][nextNum][r] = Math.min(DP[i+1][nextNum][r], DP[i][l][r] + move[l][nextNum]);    
        }
        if(nextNum != l){
            // 오른손 움직이기
            DP[i+1][l][nextNum] = Math.min(DP[i+1][l][nextNum], DP[i][l][r] + move[r][nextNum]);    
        }
    }
}