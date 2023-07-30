import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        int[] dist = new int[rocks.length+1];
        Arrays.sort(rocks);
        dist[0] = rocks[0];
        for(int i=1;i<rocks.length;i++){
            dist[i] = rocks[i] - rocks[i-1];
        }
        dist[rocks.length] = distance - rocks[rocks.length-1];
        
        // for(int i=0;i<dist.length;i++){
        //     System.out.print(dist[i] + ", ");
        // }
        // System.out.println();
        
        int s = 0;
        int e = distance;
        int res = Math.min(rocks[0], distance-rocks[0]);
        while(s<=e){
            int mid = (s+e)/2;
            // System.out.printf("s:%d m:%d e:%d%n", s,mid,e);
            if(binary(mid, dist, n)){
                s = mid + 1;
                res = mid;
            }else{
                e= mid-1;
            }
        }
        
        return res;
    }
    
    boolean binary(int distance, int[] dist, int n){
        int add = 0;
        for(int i=0;i<dist.length;i++){
            int mergedDist = add + dist[i];
            // if(distance==3){
            //     System.out.printf("mergedDist:%d%n", mergedDist);
            // }
            if(mergedDist < distance){
                add = mergedDist;
                if(--n < 0){
                    return false;
                }
            }else{
                add = 0;
            }
        }
        
        return true;
    }
}