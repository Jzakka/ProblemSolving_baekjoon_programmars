import java.util.*;
import java.util.stream.*;

class Solution {
    List<Integer>[] adjacents;
    List<Integer>[] levels;
    int[] parents;
    public long solution(int[] a, int[][] edges) {
        long sum = Arrays.stream(a).sum();
        if(sum != 0){
            return -1;
        }
        
        //init
        adjacents = new List[a.length]; //2
        levels = new List[a.length]; //2
        parents = new int[a.length]; //2
        
        for(int i=0;i<a.length;i++){
            adjacents[i] = new ArrayList();
            levels[i] = new ArrayList();
        }
        for(int[] edge:edges){
            int src = edge[0];
            int dest = edge[1];
            adjacents[src].add(dest);
            adjacents[dest].add(src);
        }
        
        // 0을 중심으로 BFS, 부모-자식 관계 형성
        Queue<Integer> Q = new LinkedList();
        Q.add(0);
        
        int level = 0;
        
        // System.out.println("BFS");
        while(!Q.isEmpty()){
            int qLen = Q.size();
            // System.out.print("\t".repeat(level));
            for(int i = 0;i<qLen;i++){
                int parent = Q.poll();
                levels[level].add(parent);
                
                // System.out.printf("%02d ", parent);
                
                List<Integer> children = adjacents[parent].stream()
                    .filter(child -> parents[parent] != child)
                    .collect(Collectors.toList());
                
                for(int child:children){
                    parents[child] = parent;
                    Q.add(child);
                }
            }
            // System.out.println();
            level++;
        }
        level--;
        
        long cnt = 0;
        
        long[] newA = Arrays.stream(a)
                .mapToLong(i->i)
                .toArray();
        
        while(level > 0){
            // System.out.println("LEVEL:" + level);
            for(int child:levels[level]){
                int parent = parents[child];
                newA[parent] += newA[child];
                cnt += Math.abs(newA[child]);
                newA[child] = 0;
            }
            level--;
        }    
        
        return cnt;
    }
}