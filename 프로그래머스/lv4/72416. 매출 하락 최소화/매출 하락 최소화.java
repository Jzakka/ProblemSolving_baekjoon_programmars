import java.util.*;

class Solution {
    int[] sales;
    Set<Integer>[] incidents;
    int[] parents;
    int[][] DP;
    int[] sub;
    public int solution(int[] sales, int[][] links) {
        this.sales = sales;
        DP = new int[sales.length + 1][2];
        sub = new int[sales.length + 1];
        Arrays.fill(sub, Integer.MAX_VALUE);
        incidents = new Set[sales.length + 1];
        parents = new int[sales.length + 1];
        
        for(int i = 0;i<incidents.length;i++){
            incidents[i] = new HashSet();
        }
        for(int[] link:links){
            int src = link[0];
            int dest = link[1];
            
            incidents[src].add(dest);
            incidents[dest].add(src);
        }
        
        for(int i=0;i<sales.length;i++){
            DP[i+1][1] = sales[i];
        }
        
        bfs();
        
        dfs();
        
        return Math.min(DP[1][0], DP[1][1]);
    }
    
    void dfs(){
        Stack<Integer> stk = new Stack();
        stk.push(-1);
        while(!stk.isEmpty()){
            int root = stk.pop();
            
            if(root > 0){
                // root가 참여하는 경우 -> child는 아무도 참여하지 않을 수도 있다.
                int parent = parents[root];
                int minDiff = Integer.MAX_VALUE;
                int minChild = 0;
                for(int incident : incidents[root]){
                   if(incident != parent){
                        DP[root][1] += Math.min(DP[incident][0],DP[incident][1]);
                       if(minDiff > DP[incident][1] - DP[incident][0]){
                           minDiff = DP[incident][1] - DP[incident][0];
                           minChild = incident;
                       }
                    }
                }
                
                // root가 불참하는 경우 -> child 중 적어도 하나는 참여해야함(참여-불참 이 가장 작은 놈으로)
                for(int incident : incidents[root]){
                   if(incident != parent){
                       if(incident  == minChild){
                            DP[root][0] += DP[incident][1];
                       }else{
                            DP[root][0] += Math.min(DP[incident][0],DP[incident][1]);
                       }
                    }
                }
                
                // System.out.printf("%d가 불참일 때 최적 : %d%n", root, DP[root][0]);
                // System.out.printf("%d가 참일 때 최적 : %d%n", root, DP[root][1]);
            }else {
                /**
                방문
                */
                stk.push(-root);
                int parent = parents[-root];
                for(int incident : incidents[-root]){
                   if(incident != parent){
                        stk.push(-incident);
                    }
                }
            }
        }        
    }
    
    void bfs(){
        Queue<Integer> Q = new LinkedList();
        Q.add(1);
        while(!Q.isEmpty()){
            int qLen = Q.size();
            
            for(int i=0;i<qLen;i++){
                int num = Q.poll();
                int parent = parents[num];
                
                for(int child:incidents[num]){
                    if(child != parent){
                        parents[child] = num;
                        Q.add(child);
                    }
                }
            }
        }        
    }
}