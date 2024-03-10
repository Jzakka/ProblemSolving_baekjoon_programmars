import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    public long solution(int n, int[] times) {
        long lo=1;
        long hi=Long.MAX_VALUE-1;
        
        long ans = hi;
        while(lo <= hi){
            long mid = (lo + hi)/2;
            // out.printf("lo:%d, mid:%d, hi:%d%n", lo,mid,hi);
                
            if(check(n, times, mid)){
                hi = mid -1;
                ans = Math.min(mid, ans);
            }else {
                lo = mid + 1;
            }
        }
        return ans;
    }
    
    boolean check(int n, int[] times, long limit){
        long passed = 0;
        for(long time: times){
            passed += limit/time;
            if(passed >= n){
                return true;
            }
        }
        return false;
    }
}