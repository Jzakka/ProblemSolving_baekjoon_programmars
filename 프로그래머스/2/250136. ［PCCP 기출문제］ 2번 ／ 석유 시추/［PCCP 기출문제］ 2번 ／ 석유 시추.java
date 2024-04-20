import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};
    int n,m;
    
    int[] groupArea;
    boolean[][] visited;
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        visited = new boolean[n][m];
        groupArea = new int[n*m+5];
        
        int groupNum = 2;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && land[i][j] == 1){
                    int area = dfs(land, i, j, groupNum, 0);
                    groupArea[groupNum++] = area;
                }
            }
        }
        
        int ans = 0;
        for(int j=0;j<m;j++){
            int sum = 0;
            Set<Integer> groups = new HashSet();
            for(int i=0;i<n;i++){
                if(land[i][j] >= 2){
                    // out.printf("만난 그룹=%d, 매장량=%d%n", land[i][j], groupArea[land[i][j]]);
                    groups.add(land[i][j]);
                }
            }
            sum = groups.stream().mapToInt(i -> groupArea[i]).sum();
            ans = Math.max(ans, sum);
        }
        return ans;
    }
    
    int dfs(int[][] land, int x, int y, int groupNum, int sum){
        Deque<int[]> stk = new ArrayDeque();
        
        stk.offerLast(new int[]{x, y});
        visited[x][y] = true;
        
        while(!stk.isEmpty()){
            int[] pos = stk.pollLast();
            x = pos[0];
            y = pos[1];
            
            land[x][y] = groupNum;
            
            sum++;
            
            for(int i=0;i<4;i++){
                int nx =x + dx[i];
                int ny = y + dy[i];
            
                if(inRange(nx, ny) && land[nx][ny] == 1 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    stk.offerLast(new int[]{nx, ny});
                }
            }
        }
        
        return sum;
    }
    
    boolean inRange(int x,int y){
        return 0<= x&& x<n&&0<=y&&y<m;
    }
}