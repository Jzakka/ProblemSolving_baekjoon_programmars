import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    List<Integer>[] graph;
    public int solution(int n, int[][] lighthouse) {
        graph = IntStream.rangeClosed(0,n)
            .mapToObj(i->new ArrayList())
            .toArray(List[]::new);
        
        for(int[] edge:lighthouse){
            int s = edge[0];
            int e = edge[1];
            
            graph[s].add(e);
            graph[e].add(s);
        }
        
        boolean[] visited = new boolean[n + 1];
        int[][] res = new int[n+1][2];
        Deque<Integer> stk = new ArrayDeque();
        // 자식 방문 전 +, 자식 방문 후 -
        stk.offerLast(1);
        while(!stk.isEmpty()){
            int node = stk.pollLast();
            // out.printf("node=%d%n", node);
            if(node > 0){
                visited[node] = true;
                stk.offerLast(-node);
                for(int child:graph[node]){
                    if(!visited[child]){
                        stk.offerLast(child);
                    }
                }
            }else{ // 자식 방문 후
                node = -node;
                for(int child:graph[node]){
                    if(res[child][0] > 0){
                        res[node][0] += Math.min(res[child][0], res[child][1]); // node를 켰을 때
                        res[node][1] += res[child][0]; // node를 껐을 때
                    }
                }
                res[node][0]++;
            }
        }
        
        return Math.min(res[1][0], res[1][1]);
    }
}