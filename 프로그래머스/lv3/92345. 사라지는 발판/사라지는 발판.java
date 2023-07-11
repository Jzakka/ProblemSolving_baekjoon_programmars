class Solution {
    final int A = 0;
    final int B = 1;
    int[][] curPos = new int[2][2];    
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    int[][] board;
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board  =board;
        curPos[0] = aloc;
        curPos[1] = bloc;
        int res = play(A, 0);
        return (int)Math.abs(res);
    }
    
    // A가 이기면 턴, B가 이기면 -턴 반환
    int play(int actor, int cnt){
        int curX = curPos[actor][0];
        int curY = curPos[actor][1];
        if(board[curX][curY] == 0){
            return cnt * (int)Math.pow(-1, actor^1);
        }
        
        int minCnt = Integer.MAX_VALUE;
        int maxCnt = cnt;
        
        for(int i=0;i<4;i++){
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];
            
            if(available(nextX, nextY)){
                // 움직인 후 턴 넘기기
                curPos[actor][0] = nextX;
                curPos[actor][1] = nextY;
                board[curX][curY] = 0;
                
                int result = play(actor^1, cnt+1);
                
                if(actor == 0){ // A의 턴일 때
                    if(result < 0){
                        maxCnt = (int)Math.max(maxCnt, Math.abs(result));
                    }else{
                        minCnt = (int)Math.min(minCnt, result);
                    }
                }else{ // B의 턴일 때 
                    if(result > 0){ 
                        maxCnt = (int)Math.max(maxCnt, result);
                    }else{
                        minCnt = (int)Math.min(minCnt, Math.abs(result));
                    }
                }
                
                // 상태 복구
                curPos[actor][0] = curX;
                curPos[actor][1] = curY;
                board[curX][curY] = 1;
            }
        }
        
        if(minCnt < Integer.MAX_VALUE){
            return minCnt * (int)Math.pow(-1,actor);
        }
        return maxCnt * (int)Math.pow(-1,actor^1);
    }
    
    boolean available(int x,int y){
        return 0<=x&&x<board.length &&0<=y&&y<board[0].length
            && board[x][y] == 1;
    }
}