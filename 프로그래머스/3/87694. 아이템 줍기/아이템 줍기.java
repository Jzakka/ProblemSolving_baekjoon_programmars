import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    int[] dx = {-1,0,0,1,-1,1,-1,1};
    int[] dy = {0,-1,1,0,1,1,-1,-1};
    int[][] board = new int[103][103];
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        for(int[] r:rectangle){
            fill(r[0]*2, r[1]*2, r[2]*2,r[3]*2);
        }
        // out.println(board[18][15]);
        // out.println(board[18][13]);
        
        int srcX = characterX * 2;
        int srcY = characterY * 2;
        int destX = itemX * 2;
        int destY = itemY * 2;
        
        Queue<int[]> Q = new LinkedList();
        boolean[][] visited = new boolean[101][101];
        Q.add(new int[]{srcX, srcY});
        visited[srcX][srcY] = true;
        
        int ans = 0;
        while(!Q.isEmpty()){
            int qLen = Q.size();
            
            for(int i=0;i<qLen;i++){
                int[] point = Q.poll();
                int x = point[0];
                int y = point[1];
                
                // out.printf("[extended]x:%d, y%d, [original]x:%d, y:%d%n", x, y, x/2, y/2);
                
                if(x==destX && y == destY){
                    return ans/2;
                }
                
                for(int d = 0;d<4;d++){
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    
                    if(inRange(nx, ny) && available(nx, ny) && !visited[nx][ny]){
                        visited[nx][ny] = true;
                        Q.add(new int[]{nx,ny});
                    }
                }
            }
            ans++;
        }
        return -1;
    }
    
    void fill(int x1,int y1, int x2, int y2){
        for(int i=x1;i<=x2;i++){
            for(int j=y1;j<=y2;j++){
                board[i][j] = 1;
            }
        }
    }
    
    boolean inRange(int x, int y){
        // out.printf("[extended]x:%d, y%d, [original]x:%d, y:%d%n", x, y, x/2, y/2);
        return 0<=x && 0<=y;
    }
    
    boolean available(int x, int y){
        if(board[x][y] == 0){
            return false;
        }
        
        
        int around = 0;
        for(int i=0;i<8;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(!inRange(nx, ny) || board[nx][ny] == 1){
                around++;
            }
        }
        // out.println("\t" + around);
        return around < 8;
    }
}