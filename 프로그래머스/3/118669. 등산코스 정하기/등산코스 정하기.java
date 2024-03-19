import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    Map<Integer, List<int[]>> graph = new HashMap<>();
    Set<Integer> gates;
    Set<Integer> summits;
    int n;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        this.n = n;
        for(int[] path:paths){
            int src = path[0];
            int dest = path[1];
            int weight = path[2];
            
            graph.computeIfAbsent(src, k->new ArrayList()).add(new int[]{dest, weight});
            graph.computeIfAbsent(dest, k->new ArrayList()).add(new int[]{src, weight});
        }
        this.gates = Arrays.stream(gates).boxed().collect(Collectors.toSet());
        this.summits = Arrays.stream(summits).boxed().collect(Collectors.toSet());
        
        int[] ans = {0, Integer.MAX_VALUE};
        for(int summit:summits){
            int intensity = getMinIntensity(summit);
            if(ans[1] > intensity || (ans[1] == intensity && ans[0] > summit)){
                ans[0] = summit;
                ans[1] = intensity;
            }
        }
        return ans;
    }
    
    int getMinIntensity(int start){
        // {노드 번호, 최소 intensity}
        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.comparingInt(a->a[1]));
        int[] intensities = new int[n+1];
        Arrays.fill(intensities, Integer.MAX_VALUE);
        
        PQ.add(new int[]{start, 0});
        intensities[start] = Integer.MAX_VALUE;
        while(!PQ.isEmpty()){
            int[] polled = PQ.poll();
            int node = polled[0];
            int intensity = polled[1];
            
            if(intensities[node] < intensity) { continue; }
            
            if(gates.contains(node)){
                return intensity;
            }
            
            for(int[] adj:graph.get(node)){
                int nextNode = adj[0];
                int maxIntensity = Math.max(intensity, adj[1]);
                
                if(!summits.contains(nextNode) && intensities[nextNode] > maxIntensity){
                    intensities[nextNode] = maxIntensity;
                    PQ.add(new int[]{nextNode, maxIntensity});
                }
            }
        }
        
        return Integer.MAX_VALUE;
    }
}