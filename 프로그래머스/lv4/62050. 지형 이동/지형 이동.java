import java.util.*;
import java.util.stream.*;

class Solution {
    static int[] dx = {1,0,0,-1};
    static int[] dy = {0,1,-1,0};
    
    Integer[] parents;
    
    Map<Set<Integer>, Integer> edges = new HashMap();
    int n,m;
    int h;
    int[][] land;
    public int solution(int[][] land, int height) {
        n = land.length;
        m = land[0].length;
        h = height;
        this.land = land;
        parents = new Integer[n*m];
        
        for(int i=0;i<n;i++){
            for(int j = 0;j<m;j++){
                if(parents[getNodeNum(i,j)] == null){
                    // System.out.printf("%d, %d%n",i,j);
                    dfs(getNodeNum(i,j));
                }
            }
        }
        
        int res = 0;
        int[][] edgesArr = edges.entrySet().stream()
            .sorted((e1,e2)->e1.getValue() - e2.getValue())
            .map(e->{
                int[] edge = {0,0,0};
                Iterator<Integer> it = e.getKey().iterator();
                edge[0] = e.getValue();
                edge[1] = it.next();
                edge[2] = it.next();
                return edge;
            })
            .toArray(int[][]::new);
        for(int[] edge : edgesArr){
            int cost = edge[0];
            int a1 = getAncestor(edge[1]);
            int a2 = getAncestor(edge[2]);
            if(a1 != a2){
                parents[a1] = a2;
                res += cost;
            }
        }
        
        return res;
    }
    
    void dfs(int root){
        // System.out.printf("\t%d,%d%n", i, j);
        Stack<Integer> stk = new Stack();
        stk.push(root);
        while(!stk.isEmpty()){
            int node = stk.pop();
            int i = node/n;
            int j = node - i*n;
            int h1 = land[i][j];
            parents[node] = root;
            
            for(int dir = 0;dir<4;dir++){
                int x = i + dx[dir];
                int y = j + dy[dir];
            
                if(available(x,y) && parents[getNodeNum(x,y)] == null){
                    int h2 = land[x][y];
                    int cost = Math.abs(h1 - h2);
                    int nextNode = getNodeNum(x,y);
                    if(cost > h){
                        edges.put(new HashSet(Arrays.asList(node, nextNode)), cost);
                    }else{
                        stk.push(nextNode);
                    }
                }
            }
        }
    }
    
    boolean available(int x, int y){
        return 0<=x && x<n && 0<=y && y<m;
    }
    
    int getNodeNum(int i,int j){
        return i*n + j;
    }
    
    int getAncestor(int p){
        while(p != parents[p]){
            p = parents[p];
        }
        return p;
    }
}