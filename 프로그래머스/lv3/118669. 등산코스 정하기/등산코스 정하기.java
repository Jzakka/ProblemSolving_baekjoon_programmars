import java.util.*;
import java.lang.*;
import java.util.stream.*;

class Solution {
    List<Node>[] adjacents;
    Set<Integer> gateSet = new LinkedHashSet();
    Set<Integer> summitSet = new LinkedHashSet();
    int[] DP;
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        adjacents = new List[n+1];
        DP = new int[n+1];
        Arrays.fill(DP, Integer.MAX_VALUE);
        for(int i = 1;i<=n;i++){
            adjacents[i] = new ArrayList();
        }
        
        for(int gate:gates){
            gateSet.add(gate);
            DP[gate] = 0;
        }
        
        for(int summit:summits){
            summitSet.add(summit);
        }
        
        for(int[] path:paths){
            int src = path[0];
            int dest = path[1];
            int cost = path[2];
            
            if(!(isGate(src) && isGate(dest)) && !(isSummit(src) && isSummit(dest))){
                if(isGate(src) || isSummit(dest)){
                    adjacents[src].add(new Node(dest,cost));    
                }else if(isGate(dest) || isSummit(src)){
                    adjacents[dest].add(new Node(src, cost));    
                }else{
                    adjacents[src].add(new Node(dest, cost));    
                    adjacents[dest].add(new Node(src, cost));        
                }
            }
        }
        
            
//     for(int i=0;i<adjacents.length;i++){
//         List<Integer> adj = adjacents[i];
//         System.out.print(i + ": ");
//         if(adj != null){
//         for(int a:adj){
//             System.out.print(a + ", ");
//         }    
//         }
        
//         System.out.println();
//     }
        
        return getResult();
    }

    
    boolean isGate(int node){
        return gateSet.contains(node);
    }
    
    boolean isSummit(int node){
        return summitSet.contains(node);
    }
    
    int[] getResult(){
        Queue<Node> Q = new LinkedList();
        
        Q.addAll(gateSet.stream().map(g->new Node(g, 0)).collect(Collectors.toList()));
        
        int[] res = {-1, Integer.MAX_VALUE};
        
        // printDP();
        while(!Q.isEmpty()){
            Node node = Q.poll();
            
            if(node.cost > DP[node.num]){
                continue;
            }
            
            // System.out.printf("POLLED NODE : %d%n", node);
            
             adjacents[node.num].stream()
                .forEach(adj->{
                    int cost = Math.max(DP[node.num], adj.cost);
                    if(cost < DP[adj.num]){
                        DP[adj.num] = cost;
                        Q.add(new Node(adj.num, DP[adj.num]));
                    }
                });
                
            
            // printDP(1);
        }
        
        for(int s:summitSet){
            if(DP[s] < res[1] || DP[s] == res[1] && s < res[0]){
                res[0] = s;
                res[1] = DP[s];
            }
        }
        
        return res;
    }
    
    static class Node{
        int num;
        int cost;
        
        Node(int num, int cost){
            this.num = num;
            this.cost = cost;
        }
    }
    
    void printDP(){
        printDP(0);
    }
    
    void printDP(int cnt){
        System.out.print("\t".repeat(cnt));
        for(int i=0;i< DP.length;i++){
            System.out.printf("%02d |", i);
        }
        System.out.println();
        System.out.print("\t".repeat(cnt));
        for(int i=0;i< DP.length;i++){
            System.out.printf("%02d |", DP[i] == Integer.MAX_VALUE ? -1:DP[i]);
        }
        System.out.println();
    }
}