import java.util.*;
import java.util.stream.*;

class Solution {
    public long solution(int[] sequence) {
        long plusAns = pulse(sequence, 1);
        long minusAns = pulse(sequence, -1);
        
        return Math.max(plusAns, minusAns);
    }
    
    long pulse(int[] seq, int mul){
        long[] pulsed = new long[seq.length];
        for(int i=0;i<seq.length;i++,mul*=-1){
            pulsed[i] = seq[i]*mul;
        }
        
        long minAcc = 0;
        long acc = 0;
        
        long res = Long.MIN_VALUE;
        for(long num:pulsed){
            acc += num;
            minAcc = Math.min(minAcc, acc);
            
            res = Math.max(res, acc - minAcc);
        }
        return res;
    }
}