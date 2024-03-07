import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (r1, r2)->{
            if(r1[0] == r2[0]){
                return r1[1] - r2[1];
            }
            return r1[0] - r2[0];
        });
        
        PriorityQueue<int[]> PQ = new PriorityQueue<>((r1, r2)->{
            if(r1[1] == r2[1]){
                return r1[0] - r2[0];    
            }
            return r1[1] - r2[1];
            
        });
        
        int ans = 0;
        for(int[] route: routes){
            boolean polled = false;
            if(!PQ.isEmpty() && PQ.peek()[1] < route[0]){
                PQ.clear();
                polled = true;
            }
                
            if(polled){
                ans++;
            }
            PQ.add(route);
        }
        
        return ++ans;
    }
}