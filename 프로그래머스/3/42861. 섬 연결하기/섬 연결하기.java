import java.util.*;
import java.util.stream.*;

class Solution {
    int[] parents;
    public int solution(int n, int[][] costs) {
        parents = IntStream.range(0, n).toArray();
        Arrays.sort(costs, Comparator.comparingInt(arr -> arr[2]));
        
        int ans = 0;
        for(int[] edge:costs){
            int src = edge[0];
            int dest = edge[1];
            int cost = edge[2];
            
            int srcAncestor = ancestor(src);
            int destAncestor = ancestor(dest);
            if(srcAncestor != destAncestor){
                parents[srcAncestor] = parents[destAncestor];
                ans += cost;
            }
        }
        
        return ans;
    }
    
    int ancestor(int node){
        while(parents[node] != node){
            node = parents[node];
        }
        return node;
    }
}