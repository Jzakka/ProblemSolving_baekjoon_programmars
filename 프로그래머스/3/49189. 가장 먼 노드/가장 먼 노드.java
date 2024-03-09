import java.util.*;
import java.util.stream.*;

class Solution {
    List<Integer>[] graph;
    public int solution(int n, int[][] edges) {
        graph = IntStream.rangeClosed(0, n)
            .mapToObj(i->new ArrayList())
            .toArray(List[]::new);
        
        for(int[] edge: edges){
            int src = edge[0];
            int dest = edge[1];
            
            graph[src].add(dest);
            graph[dest].add(src);
        }
        
        int ans = bfs();
        
        return ans;
    }
    
    int bfs(){
        Queue<Integer> Q = new LinkedList();
        boolean[] visited = new boolean[graph.length];
        
        Q.add(1);
        visited[1] = true;
        
        int qLen = 1;
        while(!Q.isEmpty()){
            qLen = Q.size();
            
            for(int i=0;i<qLen;i++){
                int node = Q.poll();
                
                graph[node].stream()
                    .filter(e -> !visited[e])
                    .peek(e -> visited[e] = true)
                    .forEach(Q::add);
            }
        }
        return qLen;
    }
}