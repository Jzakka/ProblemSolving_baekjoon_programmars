class Solution {
    public int solution(int n, int[] cores) {
        int l = 0;
        int r = cores[0] * n;
        
        if(n <= cores.length){
            return n;
        }
        
        while(l < r){
            int m = (l + r)/2;
            
            // System.out.printf("LEFT:%d MID:%d RIGHT:%d%n", l, m, r);
            
            int counts = countCores(m, cores);
            if(counts >= n){
                r = m;
            }else{
                l = m + 1;
            }
        }
        
        int time = (l+r)/2;
        int startableCounts = countCores(time, cores);
        
        int overCounted = startableCounts - n; 
        
        for(int i=cores.length-1;i>=0;i--){
            if(time%cores[i] ==0 && overCounted-- == 0){
                return i + 1;
            }
        }
        
        return -1;
    }
    
    int countCores(int time, int[] cores){
        int cnt = cores.length;
        for(int i = 0; i< cores.length;i++){
            cnt += time / cores[i];
        }
        return cnt;
    }
}