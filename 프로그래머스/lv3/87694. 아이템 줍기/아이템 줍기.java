import java.util.*;
import java.util.stream.*;

class Solution {
    /**
                E N S W     NE SE NW SW
    */
    int[] dx = {1,0,0,-1,   1,1,-1,-1};
    int[] dy = {0,1,-1,0,   1,-1,1,-1};
    
    int[][] board;
    boolean[][] visited;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        board = new int[110][110];
        visited = new boolean[110][110];
        
        for(int[] r:rectangle){
            int lx = r[0];
            int ly = r[1];
            int rx = r[2];
            int ry = r[3];
            
            board[lx*2][ly*2]++;
            board[lx*2][ry*2+1]--;
            board[rx*2+1][ly*2]--;
            board[rx*2+1][ry*2+1]++;
        }
        
        for(int i=0;i<board.length;i++){
            int sum = 0;
            for(int j = 0;j<board[0].length;j++){
                sum += board[i][j];
                board[i][j] = sum;
            }
        }
        
        for(int j = 0;j<board[0].length;j++){
            int sum = 0;
            for(int i = 0; i< board.length;i++){
                sum += board[i][j];
                board[i][j] = sum;
            }
        }
        
        // for(int i=0;i<110;i++){
        //     for(int j = 0; j<110;j++){
        //         System.out.printf("%d ", board[i][j]);
        //     }
        //     System.out.println();
        // }
        
        int[] character = {characterX*2, characterY*2};
        int[] item = {itemX*2, itemY*2};
        
        
        try{
            return dfs(board, character, item, 0)/2;    
        }catch(Exception e){return 1;}
    }
    
    int dfs(int[][] board, int[] curPos, int[] dest, int distance){
        // System.out.printf("%sX:%d, Y:%d%n", "  ".repeat(distance), curPos[0], curPos[1]);
        
        if(curPos[0] == dest[0] && curPos[1] == dest[1]){
            return distance;
        }
    
        int i=0;
        
        visited[curPos[0]][curPos[1]] = true;
        
        int minDist = Integer.MAX_VALUE;
        for(;i<4;i++){
            int nextX = curPos[0] + dx[i];
            int nextY = curPos[1] + dy[i];
            
            if(!visited[nextX][nextY] && board[nextX][nextY] != 0 && isBorder(board, nextX, nextY)){
                minDist = Math.min(minDist,dfs(board, new int[]{nextX, nextY}, dest, distance + 1));
            }
        }
        
        return minDist;
    }
    
    boolean isBorder(int[][] board, int nextX, int nextY){
        for(int i=0;i<8;i++){
            int sideX = nextX + dx[i];
            int sideY = nextY + dy[i];
            if(board[sideX][sideY] == 0){
                return true;
            }
        }
        
        return false;
    }
}