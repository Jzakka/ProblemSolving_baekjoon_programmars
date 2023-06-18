class Solution {
    public int solution(int[][] board, int[][] skill) {
        int[][] accSum = new int[board.length][board[0].length];
        
        for(int[] s:skill){
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int delta = s[0] == 1 ? -s[5] : s[5];
            
            accSum[r1][c1] += delta;
            if(c2 + 1 < accSum[0].length){
                accSum[r1][c2 + 1] -= delta;
            }
            if(r2 + 1< accSum.length){
                accSum[r2 + 1][c1] -= delta;
            }    
            if(r2 + 1< accSum.length && c2 + 1 < accSum[0].length){
                accSum[r2 + 1][c2 + 1] += delta;
            }
        }
        
        
        for(int i = 0; i< accSum.length;i++){
            int sum = 0;
            for(int j = 0;j<accSum[0].length;j++){
                sum += accSum[i][j];
                accSum[i][j] = sum;
            }
        }
        
        for(int i = 0; i< accSum[0].length;i++){
            int sum = 0;
            for(int j = 0;j<accSum.length;j++){
                sum += accSum[j][i];
                accSum[j][i] = sum;
            }
        }
        
        int res = 0;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                board[i][j] += accSum[i][j];
                if(board[i][j] > 0){
                    res++ ;
                }
            }   
        }
        
        return res;
    }
}