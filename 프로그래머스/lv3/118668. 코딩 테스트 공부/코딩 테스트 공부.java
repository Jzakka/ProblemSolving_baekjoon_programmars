import java.util.*;

class Solution {
    class Study implements Comparable<Study>{
        int reqAlp;
        int reqCop;
        int rewAlp;
        int rewCop;
        int cost;
        
        Study(int reqAlp, int reqCop, int rewAlp, int rewCop, int cost){
            this.reqAlp=reqAlp;
            this.reqCop=reqCop;
            this.rewAlp=rewAlp;
            this.rewCop=rewCop;
            this.cost=cost;
        }
        
        @Override
        public int compareTo(Study s){
            return reqCop - s.reqCop;
        }
    }
    final int N = 450;
    int[][] DP = new int[N][N];
    List<Study> unordered = new ArrayList();
    {
        unordered.addAll(Arrays.asList(new Study(0,0,0,1,1), new Study(0,0,1,0,1)));
    }
    
    public int solution(int alp, int cop, int[][] problems) {
        int[] init = {alp, cop};
        int[] dest = {Integer.MIN_VALUE, Integer.MIN_VALUE};
        
        for(int i=0;i<DP.length;i++){
            for(int j = 0;j<DP[0].length;j++){
                DP[i][j] = Integer.MAX_VALUE;
            }
        }
        DP[alp][cop] = 0;
        
        for(int[] problem:problems){
            add(problem);
            
            dest[0] = Math.max(problem[0], dest[0]);
            dest[1] = Math.max(problem[1], dest[1]);
        }
        
        if(dest[0] <= init[0] && dest[1] <= init[1]){ // destination의 x와 y모두 init보다 작거나 같은 경우
            return 0;
        }else if(dest[0] > init[0] && dest[1] < init[1]){ // destination의 x는 더 큰데 y는 init보다 작은 경우
            
            int mirroredY = 2*init[1] - dest[1];
            //x축 대칭시키고 scan
            scan(init, new int[]{N-1,N-1});
            // destination의 x에서 init의 y부터 DP의 끝까지 최솟값 탐색
            int res = Integer.MAX_VALUE;
            for(int i = init[1];i<N;i++){
                res = Math.min(res, DP[dest[0]][i]);
            }
            return res;
        }else if(dest[0] > init[0] && dest[1] < init[1]){ // destination의 y는 더 큰데 x는 init보다 작은 경우
            int mirroredX = 2*init[0] - dest[0];
            //y축 대칭시키고 scan
            scan(init, new int[]{N-1, N-1});
            // destination의 y에서 init의 x부터 DP의 끝까지 최솟값 탐색
            int res = Integer.MAX_VALUE;
            for(int i = init[0];i<N;i++){
                res = Math.min(res, DP[i][dest[1]]);
            }
            return res;
        }
        
        scan(init, new int[]{N-1,N-1});
        
        // debugDP();
        int res = Integer.MAX_VALUE;
        for(int i = dest[0];i<N;i++){
            for(int j = dest[1];j<N;j++){
                res = Math.min(res, DP[i][j]);
            }
        }
        return res;
    }
    
    void scan(int[] start, int[] end){
        int xS = start[0];
        int yS = start[1];
        int xE = end[0];
        int yE = end[1];
        
        int cnt = 1;
        
        for(int i=xS;i<=xE;i++){
            for(int j=0;i-j>=xS && j+yS<=yE;j++){
                int alp = i-j;
                int cop = yS+j;
                
                // DP업데이트 로직
                updateDP(alp, cop);
            }
        }
        
        for(int offset=1;offset<=yE;offset++){
            for(int j=0;xE-j>=xS && j+offset+yS<=yE;j++){
                int alp = xE-j;
                int cop = j+offset+yS;
                // DP 업데이트 로직
                
                updateDP(alp, cop);
            }
        }
    }
    
    void updateDP(int alp, int cop){
        if(DP[alp][cop] == Integer.MAX_VALUE){
            return;
        }
        for(Study study:unordered){
            if(study.reqAlp <= alp && study.reqCop <= cop){
                if(alp+study.rewAlp < N && cop+study.rewCop < N){
                    DP[alp+study.rewAlp][cop+study.rewCop] = Math.min(DP[alp+study.rewAlp][cop+study.rewCop], DP[alp][cop]+study.cost); 
                }
            }
        }
    }
    
    void add(int[] problem){
        int reqAlp = problem[0];
        
        Study study = new Study(
                problem[0],
                problem[1],
                problem[2],
                problem[3],
                problem[4]
            );
        unordered.add(study);
    }
    
    void debugDP(){
        for(int i=0;i<DP.length;i++){
            for(int j = 0;j<DP[0].length;j++){
                System.out.printf("%02d|",DP[i][j]);
            }
            System.out.println();
        }
    }
}