class Solution {
    int n;
    int[][] graph;
    boolean[] visited = new boolean[201];
    public int solution(int n, int[][] computers) {
        this.n = n;
        graph = computers;
        
        int ans = 0;
        for(int i=0;i<n;i++){
            if(!visited[i]){
                // System.out.println(i);
                ans++;
                dfs(i);
            }
        }
        
        return ans;
    }
    
    void dfs(int node){
        visited[node] = true;
        
        for(int i = 0;i<n;i++){
            if(graph[node][i] == 1 && !visited[i]){
                dfs(i);
            }
        }
    }
}