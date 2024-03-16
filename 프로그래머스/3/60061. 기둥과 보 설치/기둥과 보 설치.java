import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    final int PILLAR = 0;
    final int BEAM = 1;
    // space[x][y][0] : x,y좌표에 기둥이 설치돼있니
    boolean[][][] space;
    int n;
    public int[][] solution(int n, int[][] build_frame) {
        this.n = n;
        space = new boolean[n+1][n+1][2];
        
        for(int[] command : build_frame){
            int x = command[0];
            int y = command[1];
            int what = command[2];
            int action = command[3];
            
            // try{
            
            if(action == 1){
                build(x,y,what);  
            }else{
                delete(x,y,what);
            }    
                
            // }catch(Exception e){
            //     e.printStac
            //     out.print(e.getMessage());
            // }
            
        }
        
        List<int[]> ans = new ArrayList();
        for(int i=0;i<=n;i++){
            for(int j=0;j<=n;j++){
                if(space[i][j][PILLAR]){
                    ans.add(new int[]{i,j,PILLAR});
                }
                if(space[i][j][BEAM]){
                    ans.add(new int[]{i,j,BEAM});
                }
            }
        }
        
        return ans.stream().toArray(int[][]::new);
    }
    
    void build(int x, int y, int what){
        if(what==PILLAR){
            buildPillar(x,y);
        }else{
            buildBeam(x,y);
        }
    }
    
    void buildPillar(int x,int y){
        // out.printf("[PILLAR]: x=%d, y=%d", x,y);
        if(availablePillar(x,y)){
            space[x][y][PILLAR] = true;
            // out.println(" => SUCCESS");
            return;
        }
        // out.println(" => FAIL");
    }
    
    boolean availablePillar(int x,int y){
        return y == 0 ||
            (x > 0 && space[x-1][y][BEAM]) || space[x][y][BEAM] ||
            space[x][y-1][PILLAR];
    }
    
    void buildBeam(int x,int y){
        // out.printf("[BEAM]: x=%d, y=%d", x,y);
        if(availableBeam(x,y)){
            space[x][y][BEAM] = true;
            // out.println(" => SUCCESS");
            return;
        }
        // out.println(" => FAIL");
    }
    
    boolean availableBeam(int x,int y){
        return space[x][y-1][PILLAR] || (x < n && space[x+1][y-1][PILLAR]) ||
            (x >0 && x < n && space[x-1][y][BEAM] && space[x+1][y][BEAM]);
    }
    
    void delete(int x,int y,int what){
        if(what==PILLAR){
            deletePillar(x,y);
        }else{
            deleteBeam(x,y);
        }
    }
    
    void deletePillar(int x,int y){
        // out.printf("[PILLAR-delete]: x=%d, y=%d", x,y);
        space[x][y][PILLAR] = false;
        if((!inRange(x-1,y+1) || !space[x-1][y+1][BEAM] || availableBeam(x-1,y+1)) &&
          (!inRange(x,y+1) || !space[x][y+1][PILLAR] || availablePillar(x,y+1)) &&
          (!inRange(x,y+1) || !space[x][y+1][BEAM] || availableBeam(x,y+1)) &&
          (!inRange(x-1,y) || !space[x-1][y][BEAM] || availableBeam(x-1,y)) &&
          (!inRange(x,y-1) || !space[x][y-1][PILLAR] || availablePillar(x,y-1)) &&
          (!inRange(x,y) || !space[x][y][BEAM] || availableBeam(x,y))){
            // out.println(" => SUCCESS");
            return;
        }
        space[x][y][PILLAR] = true;
        // out.println(" => FAIL");
    }
    
    void deleteBeam(int x,int y){
        // out.printf("[BEAM-delete]: x=%d, y=%d", x,y);
        space[x][y][BEAM] =false;
        if((!inRange(x,y) || !space[x][y][PILLAR] || availablePillar(x,y)) &&
          (!inRange(x-1,y) || !space[x-1][y][BEAM] || availableBeam(x-1,y)) &&
          (!inRange(x,y-1) || !space[x][y-1][PILLAR] || availablePillar(x,y-1)) &&
          (!inRange(x+1,y) || !space[x+1][y][PILLAR] || availablePillar(x+1,y)) &&
          (!inRange(x+1,y) || !space[x+1][y][BEAM] || availableBeam(x+1,y)) &&
          (!inRange(x+1,y-1) || !space[x+1][y-1][PILLAR] || availablePillar(x+1,y-1))){
            // out.println(" => SUCCESS");
            return;
        }
        space[x][y][BEAM] =true;
        // out.println(" => FAIL");
    }
    
    boolean inRange(int x,int y){
        return 0<=x && x<=n && 0<= y && y<=n;
    }
}