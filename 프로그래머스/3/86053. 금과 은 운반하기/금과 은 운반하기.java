import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    int a,b;
    int[] g,s,w,t;
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        this.a = a;
        this.b = b;
        this.g = g;
        this.s = s;
        this.w = w;
        this.t = t;
        
        long lo = 1;
        long hi = IntStream.range(0,w.length)
            .mapToLong(i -> ((long)t[i] )* ((g[i]+s[i])/w[i]*2L + 1))
            .max()
            .getAsLong();
        
        long ans = hi + 1;
        while(lo <= hi){
            long mid = lo + (hi - lo)/2;
            
            if(check(mid)){
                hi = mid - 1;
                ans = Math.min(mid, ans);
            }else{
                lo = mid + 1;
            }
        }
        
        return ans;
    }
    
    boolean check(long mid){
        long[] loads = getLoad(mid);
        // out.printf("mid:%d%n", mid);
        
        long sum = Arrays.stream(loads)
            // .peek(i -> out.println("\t"+i))
            .sum();
        if(a + b > sum){
            // out.printf("\tsum:%d%n", sum);
            return false;
        }
        
        long gold = 0;
        long silver = 0;
        
        for(int i=0;i<loads.length;i++){
            gold += Math.min(g[i], loads[i]);
            silver += Math.min(s[i], loads[i]);
        }
        
        return gold >= a && silver >= b;
    }
    
    long[] getLoad(long time){
        long[] loads = new long[w.length];
        for(int i = 0;i<loads.length;i++){
            if(time >= t[i]){
                long load = 1;
                load += (time - t[i]) / (2*t[i]);
                load *= w[i];
                loads[i] = Math.min(load, g[i] + s[i]);
            }
        }
        return loads;
    }
}