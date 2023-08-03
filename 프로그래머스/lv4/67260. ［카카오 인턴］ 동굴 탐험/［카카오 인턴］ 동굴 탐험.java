import java.util.*;

class Solution {
    int[] parents;
    boolean[] DP; // i번 노드를 방문했는지 여부
    
    Map<Integer, Integer> preVisit = new HashMap(); // key 이전에 value를 방문해야함
    Map<Integer, Integer> postVisit = new HashMap(); // key를 방문하면 value 방문 가능
    
    public boolean solution(int n, int[][] path, int[][] order) {
        parents = new int[n];
        DP = new boolean[n];
        
        LinkedHashSet<Integer>[] edges = new LinkedHashSet[n];
        for(int i = 0;i<n;i++){
            edges[i] = new LinkedHashSet();
        }
        
        for(int[] p:path){
            edges[p[0]].add(p[1]);
            edges[p[1]].add(p[0]);
        }
        
        Queue<Integer> Q = new LinkedList();
        Q.add(0);
        
        while(!Q.isEmpty()){
            int qLen = Q.size();
            
            for(int i = 0;i<qLen;i++){
                int parent = Q.poll();
                
                for(int child : edges[parent]){
                    parents[child] = parent;
                    edges[child].remove(parent);
                    Q.add(child);
                }
            }
        }
        
        for(int[] o:order){
            if(o[1] == 0){
                return false;
            }
            preVisit.put(o[1], o[0]);
            postVisit.put(o[0], o[1]);
        }
        
        Q.add(0);
        DP[0] = true;
        while(!Q.isEmpty()){
            int qLen = Q.size();
            for(int i=0;i<qLen;i++){
                int node = Q.poll();
                
                if(postVisit.containsKey(node)){
                    int post = postVisit.get(node);
                    if(!DP[post] && DP[parents[post]]){
                        DP[post] = true;
                        Q.add(post);
                    }
                }
                
                for(int child:edges[node]){
                    if(!DP[child]){
                        // 선방문이 존재하는 경우
                        boolean hasPreVisit = preVisit.containsKey(child);
                        if(hasPreVisit && DP[preVisit.get(child)]){
                            DP[child] = true;
                            Q.add(child);
                        }
                        // 그렇지 않은 경우
                        else if(!hasPreVisit){
                            DP[child] = true;
                            Q.add(child);
                        }
                    }
                }
            }
        }
        
        for(int post : preVisit.keySet()){
            if(!DP[post]){
                return false;
            }
        }
        return true;
    }
}