class Solution {
    public int solution(int n, int[] stations, int w) {
        int ans = 0;
        int cursor = 1;
        
        int idx = 0;
        
        while(cursor <= n){
            while(idx < stations.length && stations[idx] - w <= cursor && cursor <= stations[idx] + w){
                cursor = stations[idx++] + w + 1;
            }
            if(cursor <= n){
                ans++;    
            }
            cursor += 2*w  + 1;
        }
        
        return ans;
    }
}