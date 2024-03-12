import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    List<Integer>[] graph;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        graph = IntStream.rangeClosed(0,n).mapToObj(i->new ArrayList())
            .toArray(List[]::new);
        
        for(int[] road:roads){
            int src = road[0];
            int dest =road[1];
            
            graph[src].add(dest);
            graph[dest].add(src);
        }
        
        int[] distances = bfs(n, destination);
        return Arrays.stream(sources)
            .map(i->distances[i])
            .toArray();
    }
    
    int[] bfs(int n, int start){
        int[] distances = new int[n + 1];
        Arrays.fill(distances, -1);
        Queue<Integer> Q = new LinkedList();
        distances[start] = 0;
        Q.add(start);
        
        int dist = 0;
        while(!Q.isEmpty()){
            int qLen = Q.size();
            
            for(int i=0;i<qLen;i++){
                int node = Q.poll();
                
                for(int adj:graph[node]){
                    if(distances[adj] == -1){
                        distances[adj] = dist + 1;
                        Q.add(adj);
                    }    
                }
            }
            
            dist++;
        }
        return distances;
    }
}