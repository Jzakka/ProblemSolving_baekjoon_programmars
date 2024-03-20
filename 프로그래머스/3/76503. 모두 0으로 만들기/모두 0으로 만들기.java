import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    final int ROOT = Integer.MAX_VALUE/2;
    final int NON = Integer.MAX_VALUE;
    int n;
    int root = 0;
    int[] parents;
    List<Integer>[] graph;
    List<Integer> leaves = new ArrayList();
    long[] weights;
    public long solution(int[] a, int[][] edges) {
        int total = Arrays.stream(a).sum();
        if(total != 0){
            return -1;
        }
        n = a.length;
        weights = Arrays.stream(a).mapToLong(i -> (long)i).toArray();
        
        root = IntStream.range(0, n)
            .reduce(0, (max, idx)->{
                if(Math.abs(a[idx]) > Math.abs(a[max])){
                    return idx;
                }
                return max;
            });
        
        parents = new int[n];
        Arrays.fill(parents, NON);
        parents[root] = ROOT;
        
        graph = IntStream.range(0, n)
            .mapToObj(i -> new ArrayList())
            .toArray(List[]::new);
        
        for(int[] edge: edges){
            int src = edge[0];
            int dest = edge[1];
            
            graph[src].add(dest);
            graph[dest].add(src);
        }
        
        findLeaf();
        
        // printLeaf();
        
        long ans =  bottomUp();
        // if(ans == -1){
        //     throw new RuntimeException();
        // }
        return ans;
    }
    
    long bottomUp(){
        // {노드번호, 자식 방문 여부}
        Deque<int[]> stk = new ArrayDeque<>();
        stk.offerLast(new int[]{root, 0});
        long cnt = 0;
        
        while(!stk.isEmpty()){
            int[] polled = stk.pollLast();
            int node = polled[0];
            int isPost = polled[1];
            
            // out.printf("Current node is %d.%n", node);
            
            if(isPost == 0){ // 아직 자식을 방문하기 전임
                // out.printf("\tI need to visit children.%n");
                stk.offerLast(new int[]{node, 1});
                for(int child:graph[node]){
                    if(child != parents[node]){
                        // out.printf("\t%d added to Stack.%n", child);
                        stk.offerLast(new int[]{child, 0});
                    }
                }
            }else { // 이미 자식 방문했음, 로직 수행
                // out.printf("\tI need to proceed logic.%n");
                if(node == root){
                    break;
                }
                long weight = weights[node];
                cnt += Math.abs(weight);
                weights[parents[node]] += weight;
            }
        }
        return cnt;
    }
    
    void findLeaf(){
        Deque<Integer> stk = new ArrayDeque();
        
        // out.println("root:" + root);
        stk.offerLast(root);
        
        while(!stk.isEmpty()){
            int node = stk.pollLast();
            
            if(graph[node].size() == 1 && parents[node] == graph[node].get(0)){
                leaves.add(node);
                continue;
            }
            
            for(int child:graph[node]){
                if(child != parents[node]){
                    parents[child] = node;
                    stk.offerLast(child);
                }
            }
        }
    }
    
    void printLeaf(){
        out.println(leaves);
    }
}