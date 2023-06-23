import java.util.*;
import java.lang.*;
import java.util.stream.*;

class Solution {
    List<Integer>[] adjacents;
    Map<Long, Integer> costs = new HashMap();
    Set<Integer> gateSet = new LinkedHashSet();
    Set<Integer> summitSet = new LinkedHashSet();
    int[] DP;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        adjacents = new List[n+1];
        DP = new int[n+1];
        Arrays.fill(DP, Integer.MAX_VALUE);
        for(int i = 1;i<=n;i++){
            adjacents[i] = new ArrayList();
        }
        
        for(int gate:gates){
            gateSet.add(gate);
            DP[gate] = 0;
        }
        
        for(int summit:summits){
            summitSet.add(summit);
        }
        
        for(int[] path:paths){
            int src = path[0];
            int dest = path[1];
            int cost = path[2];
            adjacents[src].add(dest);
            adjacents[dest].add(src);
            
            costs.put(cantorPair(src,dest), cost);
            costs.put(cantorPair(dest,src), cost);
        }
        
        return getResult();
    }
    
    int[] getResult(){
        TreeSet<Integer> heap = new TreeSet<>((a,b)->{
            if(DP[a] == DP[b]){
                return a -b;
            }
            return DP[a] - DP[b];
        });
        Set<Integer> visited = new HashSet();
        
        heap.addAll(gateSet);
        
        int[] res = {-1, Integer.MAX_VALUE};
        
        while(!heap.isEmpty()){
            int node = heap.first();
            heap.remove(node);
                        
            if(summitSet.contains(node)){
                
                // 결과 갱신
                if(DP[node] < res[1] || (DP[node] == res[1] && node < res[0])){
                    res[0] = node;
                    res[1] = DP[node];
                }
                
                continue;
            }
            
            visited.add(node);
            
             List<Integer> fringes = adjacents[node].stream()
                .filter(adj->{
                    int cost = Math.max(DP[node], costs.get(cantorPair(node, adj)));
                    if(cost < DP[adj] && !gateSet.contains(adj) && !visited.contains(adj)){
                        if(heap.contains(adj)){
                            heap.remove(adj);
                        }
                        DP[adj] = cost;
                        return true;
                    }
                    return false;
                })
                .collect(Collectors.toList());
            
            
            heap.addAll(fringes);            
        }
        
        return res;
    }
    
    long cantorPair(long a, long b){
        return (a+b)*(a+b+1)/2 + b;
    }
}