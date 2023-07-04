import java.util.*;

class Solution {
    int n;
    long G;
    long S;
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        n = g.length;
        G = a;
        S = b;
        
        long l = 0;
        long r = -1;
        // 오른쪽 끝 설정
        long cityTotal=a + b;
        for(int i=0;i<n;i++){
            long count = cityTotal/w[i]+1;
            r = Math.max(r, 2*count*t[i]);
        }
        
        
        while(l < r){
            long m = (l+r)/2;
            
            if(available(m,g,s,w,t)){
                r = m;
            }else{
                l = m+1;
            }
        }
        
        return l;
    }
    
    
    boolean available(long time, int[] g,int[] s,int[] w,int[] t){
        long[] totalTransfer = getTotalTransfer(time, g,s,w, t);
        
        // 총 운반 가능량의 합이 요구량이상이어야함
        if(G+S > Arrays.stream(totalTransfer).sum()){
            return false;
        }
        
        long totalGold = 0;
        long totalSilver = 0;
        // 금,은 최대량
        for(int i = 0; i< n;i++){            
            if(totalTransfer[i] < g[i]){
                totalGold += totalTransfer[i];   
            }else{
                totalGold += g[i];
            }
            
            if(totalTransfer[i] < s[i]){
                totalSilver += totalTransfer[i];
            }else{
                totalSilver += s[i];
            }
        }
        
        if(totalGold >= G && totalSilver >= S){
            return true;
        }
        
        return false;
    }
    
    long[] getTotalTransfer(long time,int[] g,int[] s,int[] w, int[] t){
        long[] totalTransfer = new long[n];
        for(int i = 0; i< n;i++){
            long copiedTime = time;
            long cnt = 0;
            
            if(copiedTime >= t[i]){
                cnt++;
                copiedTime -= t[i];
            }
            cnt += copiedTime/(2*t[i]);
            
            totalTransfer[i] =  Math.min(g[i]+s[i], cnt * w[i]);
        }
        return totalTransfer;
    }
}