import java.util.*;

class Solution {
    List<Integer>[] edges;
    int[] parents;
    int[] childrenCnt;
    
    int cnt = 0;
    public int solution(int n, int[][] lighthouse) {
        edges = new List[n+1];
        parents = new int[n+1];
        childrenCnt = new int[n+1];
        
        for(int i=1;i<edges.length;i++){
            edges[i] = new ArrayList();
        }
        for(int[] l:lighthouse){
            int src = l[0];
            int dest = l[1];
            
            edges[src].add(dest);
            edges[dest].add(src);
        }
        parents[1] = -1;
        setParents(1);
        
        postOrder(1);
        return cnt;
    }
    
    int postOrder(int cur){
        if(childrenCnt[cur] == 0){
            // System.out.println("CUR:" + cur + ", return 1");
            return 1;
        }
        
        int sum = 0;
        for(int child:edges[cur]){
            if(child != parents[cur]){
                sum += postOrder(child);
            }
        }
        
        if(sum == 0){
            // System.out.println("CUR:" + cur + ", return 1");
            return 1;
        }
        
        // System.out.println("CUR:" + cur + ", return 0");
        cnt++;
        return 0;
    }
    
    void setParents(int parent){
        for(int children:edges[parent]){
            if(children != parents[parent]){
                parents[children] = parent;
                childrenCnt[parent]++;
                setParents(children);
            }
        }
    }
}

