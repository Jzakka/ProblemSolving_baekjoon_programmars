import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    final int UP = 0;
    final int LEFT = 1;
    final int RIGHT = 2;
    final int DOWN  = 3;
    
    final int[] dx = {-1,0,0,1};
    final int[] dy = {0,-1,1,0};
    int[][][] DP;
    int n , m;
    public int solution(int[][] board) {
        n = board.length;
        m = board[0].length;
        // DP[x][y][d] : (x,y)를 d방향에서 진입했을 때 드는 최소 비용
        DP = new int[n][m][4];
        Arrays.stream(DP)
            .forEach(mat -> Arrays.stream(mat).forEach(row -> Arrays.fill(row, Integer.MAX_VALUE)));
        DP[0][0][DOWN] = DP[0][0][RIGHT] = 0;
        
        dfs(board, 0,0,DOWN);
        dfs(board, 0,0,RIGHT);
        // debug();
        return Math.min(DP[n-1][m-1][DOWN], DP[n-1][m-1][RIGHT]);
    }
    
    void dfs(int[][] board, int x,int y, int curDir){
        
        for(int dir=0;dir<4;dir++){
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            int cost = getCost(curDir, dir);
            if(dir + curDir != 3 && inRange(nx,ny) && board[nx][ny] == 0 && DP[nx][ny][dir] > DP[x][y][curDir] + cost){
                DP[nx][ny][dir] = DP[x][y][curDir] + cost;
                dfs(board, nx, ny, dir);
            }
        }
    }
    
    int getCost(int dir1, int dir2){
        if(dir1 == dir2){
            return 100;
        }
        return 600;
    }
    
    boolean inRange(int x, int y){
        return 0<=x && x<n && 0<=y&&y<m;
    }
    
    void debug(){
        out.println("===DEBUG===");
        for(int[][] mat:DP){
            for(int[] row:mat){
                out.print("[");
                for(int e: row){
                    out.print(e + ",");
                }
                out.print("]");
            }
            out.println();
        }
    }
}