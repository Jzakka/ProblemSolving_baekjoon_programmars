import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    public int solution(int n, int[] cores) {
        long lo = 0;
        long hi = 1_000_000_000;
        
        if(n<=cores.length){
            return n;
        }
        
        long time = hi + 1;
        while(lo<=hi){
            long mid = lo + (hi - lo)/2;
            
            if(check(n, mid, cores)){
                hi = mid - 1;
                time = Math.min(time, mid);
            }else{
                lo = mid + 1;
            }
        }
                
        long started = count((int)time, cores);
        long overCounted = started - n;
        
        for(int i=cores.length-1;i>=0;i--){
            if(time%cores[i] == 0 && overCounted-- == 0){
                return i + 1;
            }
        }
        
        return -1;
    }
    
    boolean check(int n, long t, int[] cores){
        return n <= count((int)t, cores);
    }
    
    // 전체 코어들이 작업을 시작한 횟수
    long count(int t, int[] cores){
        long cnt = cores.length;
        for(int core:cores){
            cnt += t/core;
        }
        return cnt;
    }
}