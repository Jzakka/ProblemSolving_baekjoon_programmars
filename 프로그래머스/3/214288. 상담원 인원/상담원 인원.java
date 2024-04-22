import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    public int solution(int k, int n, int[][] reqs) {
        int[] mentoCnts = new int[k + 1];
        int ans = getAns(mentoCnts, k, n, reqs, 1, 0);
        return ans;
    }
    
    public int getAns(int[] mentoCnts, int k, int n, int[][] reqs, int type, int usedMento){
        int remainMento = n - usedMento;
        if(type == k){
            mentoCnts[type] = remainMento;
            
            for(int i=1;i<mentoCnts.length;i++){
                // out.printf("type#%d에 할당된 멘토 수=%d%n", i, mentoCnts[i]);
            }
            
            
            int[][] mentos = Arrays.stream(mentoCnts).mapToObj(i -> new int[i]).toArray(int[][]::new);
            return calc(k, mentos, reqs);
        }
        
        int ans = Integer.MAX_VALUE;
        for(int i = 1;i<remainMento;i++){
            mentoCnts[type] = i;
            ans = Math.min(ans, getAns(mentoCnts, k, n, reqs, type+1, usedMento + i));
        }
        return ans;
    }
    
    /*
    [
        [], 
        [e1_1, e1_2,..], <= mentos[1]: 1유형의 멘토들의 상담 종료 시간
        [e2_2, e2_2,...],
    ]
    */
    public int calc(int k, int[][] mentos, int[][] reqs){
        int sum = 0;
        for(int[] req:reqs){
            int reqTime = req[0];
            int processTime = req[1];
            int type = req[2];
            
            
            if(mentos[type].length == 0){
                return Integer.MAX_VALUE;
            }
            
            // out.printf("  req type=%d, reqTime=%d, processTime=%d%n", type, reqTime, processTime);
            
            int minIdx = 0;
            for(int i = 0; i<mentos[type].length;i++){
                if(mentos[type][i] < mentos[type][minIdx]){
                    minIdx = i;
                }
            }
            int e = mentos[type][minIdx];
            int latency = e - reqTime;
            // out.printf("  latency=%d%n", latency);
            if(latency > 0){
                sum += latency;
                // out.printf("  sum=%d%n", sum);
            }
            mentos[type][minIdx] = Math.max(e, reqTime) + processTime;
        }
        return sum;
    }
}