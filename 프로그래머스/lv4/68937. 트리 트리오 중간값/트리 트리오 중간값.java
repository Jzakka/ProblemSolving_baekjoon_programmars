import java.util.*;

class Solution {
    public int solution(int n, int[][] edges) {
        List<Integer>[] incidents = new List[n + 1];
        List<Integer>[] lvNodes = new List[n+1];
        int[] levels = new int[n+1];
        int[] subLevels = new int[n+1];
        boolean[] bfsVisited = new boolean[n+1];
        int[] parents = new int[n + 1];
        for(int i=0;i<=n;i++){
            incidents[i] = new ArrayList();
            lvNodes[i] = new ArrayList();
        }
        
        for(int[] edge:edges){
            incidents[edge[0]].add(edge[1]);
            incidents[edge[1]].add(edge[0]);
        }
        
        Queue<Integer> Q = new LinkedList();
        Q.add(1);
        int src = 1;
        
        while(!Q.isEmpty()){
            int qLen = Q.size();
            for(int i=0;i<qLen;i++){
                int node = Q.poll();
                src = node;
                // System.out.println("farest = " + src);
                bfsVisited[node] = true;
                for(int incident : incidents[node]){
                    // System.out.println("incident = " + incident);
                    if(!bfsVisited[incident]){
                        Q.add(incident);
                    }
                }
            }
        }
        
        
        Q.add(src);
        parents[src] = 0;
        int lv = 0;
        int dest = 0;
        
        while(!Q.isEmpty()){
            int qLen = Q.size();
            for(int i=0;i<qLen;i++){
                int node= Q.poll();
                int parent = parents[node];
                
                lvNodes[lv].add(node);
                levels[node] = lv;    
                
                dest = node;
                
                for(int incident:incidents[node]){
                    if(parent != incident){
                        parents[incident] = node;
                        Q.add(incident);
                    }
                }
                
            }
            lv++;
        }
            
        int res = lv - 1;
        Set<Integer> paths = new HashSet();
        while(dest != 0){
            paths.add(dest);
            dest = parents[dest];
        }
        
        for(int i=lv;i>0;i--){
            for(int node : lvNodes[i]){
                if( paths.contains(parents[node]) && !paths.contains(node)){
                    if(levels[parents[node]] == subLevels[node] + 1 
                       || levels[parents[node]] + subLevels[node] + 1 == res){
                        return res;    
                    }
                }
                subLevels[parents[node]] = Math.max(subLevels[node] + 1, subLevels[parents[node]]);
            }
        }
        return res - 1;
    }
}