import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    Map<String, List<String>> graph = new HashMap();
    Map<String, Set<Integer>> visitedEdges = new HashMap();
    
    Deque<String> ans = new ArrayDeque();
    
    public String[] solution(String[][] tickets) {
        for(String[] ticket:tickets){
            String src = ticket[0];
            String dest = ticket[1];
            
            graph.computeIfAbsent(src, k->new ArrayList()).add(dest);
            visitedEdges.computeIfAbsent(src, k->new HashSet());
        }
        
        graph.forEach((k,v)->v.sort((s1,s2)->s1.compareTo(s2)));
        
        dfs("ICN",tickets.length,0);            
        
        return ans.stream().toArray(String[]::new);
    }
    
    boolean dfs(String node, final int ticketLen,int ticketUsed){
        if(ticketUsed == ticketLen){
            ans.offerFirst(node);
            return true;
        }
        
        List<String> adjacents = graph.get(node);
        
        for(int i=0;adjacents != null && i<adjacents.size();i++){
            Set<Integer> visited = visitedEdges.get(node);
            
            if(!visited.contains(i)){
                visited.add(i);
                
                String nextNode= adjacents.get(i);                          
                
                if(dfs(nextNode, ticketLen, ticketUsed+1)){
                    ans.offerFirst(node);
                    return true;
                }
                
                visited.remove(i);
            }
        }
        return false;
    }
}