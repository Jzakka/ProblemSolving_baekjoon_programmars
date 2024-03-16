import java.util.*;
import java.util.stream.*;

import java.lang.System.*;

class Solution {
    Integer[] parents;
    Integer[][] children;
    int[] type;
    final int SHEEP = 0;
    
    int n;
    public int solution(int[] info, int[][] edges) {
        n = info.length;
        parents = new Integer[n];
        children = IntStream.range(0,n).mapToObj(i->new Integer[2]).toArray(Integer[][]::new);
        type = info;
        
        for(int[] edge : edges){
            int src = edge[0];
            int dest = edge[1];
            
            if(children[src][0] == null){
                children[src][0] = dest;
            }else{
                children[src][1] = dest;
            }
            parents[dest] = src;
        }
        
        return travel(0, 0, 0, 0, new LinkedList());
    }
    
    int travel(int current, int sheep, int wolf, int res, List<Integer> reserved){
        if(type[current] == SHEEP){
            sheep++;
        }else{
            wolf++;
        }
        
        if(sheep <= wolf){
            return res;
        }
        res = Math.max(res, sheep);
        
        Integer left = children[current][0];
        Integer right = children[current][1];
        if(left != null){
            reserved.add(left);
        }
        if(right != null){
            reserved.add(right);
        }
        
        for(Integer nextNode:reserved){
            List<Integer> copiedReserved = new LinkedList(reserved);
            copiedReserved.remove(nextNode);
            res = Math.max(res, travel(nextNode, sheep, wolf, res, copiedReserved));
        }
        
        return res;
    }
}