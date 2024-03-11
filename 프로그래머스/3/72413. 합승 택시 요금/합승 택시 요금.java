import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    List<int[]>[] graph;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        graph = IntStream.rangeClosed(0, n)
            .mapToObj(i -> new ArrayList())
            .toArray(List[]::new);
        
        for(int[] fare:fares){
            int src = fare[0];
            int dest = fare[1];
            int cost = fare[2];
            
            graph[src].add(new int[]{dest, cost});
            graph[dest].add(new int[]{src, cost});
        }
        
        int[] distances = dijkstra(s);
        int ans = Integer.MAX_VALUE;
        for(int i=1;i<=n;i++){
            int sToI = distances[i];
            int[] branches = dijkstra(i);
            int iToA = branches[a];
            int iToB = branches[b];
            
            ans = Math.min(ans, sToI + iToA + iToB);
        }
        
        return ans;
    }
    
    int[] dijkstra(int start){
        // {노드, 거리}
        PriorityQueue<int[]> PQ = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[1]));
        int[] distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        
        PQ.add(new int[]{start, 0});
        distances[start] = 0;
        
        while(!PQ.isEmpty()){
            int[] polled = PQ.poll();
            int node = polled[0];
            int distance = polled[1];
            
            if(distance > distances[node]){
                continue;
            }
            
            for(int[] adj : graph[node]){
                int nextNode = adj[0];
                int cost = adj[1];
                if(distances[nextNode] > distance + cost){
                    distances[nextNode] = distance + cost;
                    PQ.add(new int[]{nextNode, distances[nextNode]});
                }
            }
        }
        
        return distances;
    }
}