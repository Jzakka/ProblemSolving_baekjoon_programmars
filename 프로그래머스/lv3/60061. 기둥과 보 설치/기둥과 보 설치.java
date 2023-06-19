import java.util.*;

class Solution {
    boolean[][][][] line;
    TreeSet<Built> resultSet = new TreeSet();
    int n;
    
    public int[][] solution(int n, int[][] build_frame) {
        //init
        this.n = n;
        line = new boolean[n+1][n+1][n+1][n+1];
        
        //build
        for(int[] build : build_frame){
            apply(build);
        }
        //result
        return resultSet.stream().map(b->new int[]{b.x, b.y, b.type}).toArray(int[][]::new);
    }
    
    void apply(int[] build){
        int x = build[0];
        int y = build[1];
        int type = build[2];
        int action = build[3];
        
        try{
             
        if(type == 0 && action == 0){ // 기둥 삭제
            deletePillar(x,y);
        }else if(type == 0 && action == 1){ // 기둥설치
            buildPillar(x,y);
        }else if(type == 1 && action == 0){ // 보 삭제
            deleteBeam(x,y);
        }else { // 보설치
            buildBeam(x,y);
        }    
            
        }catch(Exception e){
            System.out.println("EXCEPTION!!!!");
            while(true);
        }
        
    }
    
    // 교차점 양옆 중 한쪽이라도 보가 있는지 확인
    boolean thereAreBeams(int x, int y){
        if(x < 0 || x > n || y < 0 || y > n){
            return false;
        }
        
        return (x-1 >= 0 && line[x-1][y][x][y])
            || (x+1 <= n && line[x+1][y][x][y]);
    }
    
    // 보의 양옆 모두 보가 있는지 확인
    boolean thereAreBeamsBoth(int x, int y){
        if(x < 0 || x > n || y < 0 || y > n){
            return false;
        }
        
        return (x-1 >= 0 && line[x-1][y][x][y])
            && (x+2 <= n && line[x+1][y][x+2][y]);
    }
    
    boolean thereIsBeam(int x, int y){
        if(x < 0 || x > n || y < 0 || y > n){
            return false;
        }
        
        if(x+1 > n){
            return false;
        }
        
        return line[x][y][x+1][y];
    }
    
    void buildPillar(int x, int y){
        // System.out.println("buildPillar" + "(" + x+ ", "+ y + ")");
        if(y == 0 || thereAreBeams(x,y) || isPillarSupports(x,y)){
            line[x][y][x][y+1] = true;
            line[x][y+1][x][y] = true;
            resultSet.add(new Built(x,y,0));
            // System.out.println("\tpillar built at " + x +", " + y);
        }
    }
    
    void deletePillar(int x, int y){
        // System.out.println("deletePillar" + "(" + x+ ", "+ y + ")");
        
        // 기둥위에 기둥이 없어야함
        if(isPillarSupports(x,y+2) && !thereAreBeams(x,y+1)){
            return; 
        }
        
        // 지우려는 기둥 왼쪽에 보가 있는경우
        if(thereIsBeam(x-1, y+1)){
            // 기둥 왼쪽 보를 지탱하는 기둥이 있으면 ㄱㅊ
            // 없으면 왼쪽보 왼쪽, 오른쪽으로 둘다 보가 있어야함
            if(!isPillarSupports(x-1,y+1)){
                if(!thereAreBeamsBoth(x-1, y+1)){
                    return;
                }
            }
        }
        // 지우려는 기둥 오른쪽에 보가 있는경우
        if(thereIsBeam(x, y+1)){
            // 기둥 오른쪽 보를 지탱하는 기둥이 있으면 ㄱㅊ
            // 없으면 오른쪽보 왼쪽, 오른쪽으로 둘다 보가 있어야함
            if(!isPillarSupports(x+1,y+1)){
                if(!thereAreBeamsBoth(x, y+1)){
                    return;
                }
            }
        }
        
        line[x][y][x][y+1] = line[x][y+1][x][y] = false;
        resultSet.remove(new Built(x,y,0));
        // System.out.println("\tpillar removed at " + x +", " + y);
    }
    
    boolean isPillarSupports(int x,int y){
        if(x < 0 || x > n || y < 0 || y > n){
            return false;
        }
        
        return line[x][y][x][y-1];
    }
    
    
    void buildBeam(int x, int y){
        // System.out.println("buildBeam" + "(" + x+ ", "+ y + ")");
        // (x, y)나 (x+1, y)를 받치는 기둥이 있거나
        // (x-1, y), (x+1, y)모두 보가 있어야한다.
        
        if((isPillarSupports(x,y) || isPillarSupports(x+1,y)) || thereAreBeamsBoth(x,y)){
            line[x][y][x+1][y] = line[x+1][y][x][y] = true;
            resultSet.add(new Built(x,y,1));
            
            // System.out.println("\tbeam built at " + x +", " + y);
        }
    }
    
    void deleteBeam(int x, int y){
        // System.out.println("deletedBeam" + "(" + x+ ", "+ y + ")");
        // 보위에 기둥이 있으면 기둥 밑둥을 지탱해주는 기둥이 있어야함
        
        if(isPillarSupports(x,y+1)){
            if(!isPillarSupports(x,y) && !thereIsBeam(x-1, y)){
                return;
            }
        }
        
        if(isPillarSupports(x+1,y+1)){
            if(!isPillarSupports(x+1,y) && !thereIsBeam(x+1, y)){
                return;
            }
        }
        
        // 보왼쪽의 보를 지탱해주는 기둥이 있어야함
        if(thereIsBeam(x-1, y)){
            if(!isPillarSupports(x-1,y) && !isPillarSupports(x,y)){
                return;
            }
        }
        
        // 보오른쪽의 보를 지탱해주는 기둥이 있어야함
        if(thereIsBeam(x+1, y)){
            if(!isPillarSupports(x+2,y) && !isPillarSupports(x+1,y)){
                return;
            }
        }
        
        line[x][y][x+1][y] = line[x+1][y][x][y] = false;
        resultSet.remove(new Built(x,y,1));
        
        // System.out.println("\tbeam removed at " + x +", " + y);
    }
    
    static class Built implements Comparable<Built>{
        int x;
        int y;
        int type;
        
        Built(int x,int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }
        
        @Override
        public int compareTo(Built b){
            if(x == b.x){
                if(y == b.y){
                    return type - b.type;
                }   
                return y - b.y;
            }
            return x - b.x;
        }
        
        @Override
        public boolean equals(Object o){
            return ((Built)o).x == x
                && ((Built)o).y == y
                && ((Built)o).type == type;
        }
    }
}