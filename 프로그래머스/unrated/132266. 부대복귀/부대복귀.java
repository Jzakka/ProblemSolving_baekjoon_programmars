import java.util.*;

class Solution {
    Set<Integer> visited = new HashSet();
    Map<Integer, List<Integer>> adjacents = new HashMap();
    Map<Integer, Integer> dists = new LinkedHashMap();
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {   
        for(int src:sources){
            dists.put(src, -1);
        }
        
        for(int[] road : roads){
            int src = road[0];
            int dest = road[1];
            
            link(src, dest);
            link(dest, src);
        }
        
        bfs(destination);    
        
        return dists.values().stream().mapToInt(Integer::intValue).toArray();
    }
    
    void link(int src, int dest){
        if(!adjacents.containsKey(src)){
            List<Integer> friends = new ArrayList();
            friends.add(dest);
            
            adjacents.put(src, friends);
        }else{
            adjacents.get(src).add(dest);
        }
    }
    
    int bfs(int src){
        Queue<Integer> Q = new LinkedList();
        Q.offer(src);
        visited.add(src);
        int dist = -1;
        
        // System.out.println("SRC:"+src);
        
        while(!Q.isEmpty()){
            int qLen = Q.size();
            
            for(int i = 0; i< qLen;i++){
                int node = Q.poll();
                
                if(dists.containsKey(node)){
                    // System.out.println("\tNODE:"+node +" DIST:" + (dist+1));    
                    dists.put(node, dist+1);
                }
                
                List<Integer> nexts = new ArrayList();
                if(adjacents.containsKey(src)){
                    for(int adj:adjacents.get(node)){
                        if(!visited.contains(adj)){
                            visited.add(adj);
                            nexts.add(adj);
                        }
                    }    
                }
                Q.addAll(nexts);
            }
            
            dist++;
        }
        return -1;
    }
}