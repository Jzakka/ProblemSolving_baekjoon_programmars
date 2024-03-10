import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    public int solution(int[][] jobs) {
        int last = 0;
        Arrays.sort(jobs, Comparator.comparingInt(arr -> arr[0]));
        int ans = 0;
        PriorityQueue<int[]> PQ = new PriorityQueue(Comparator.comparingInt((int[] arr) -> arr[1]));
        int i=0;
        
        int finishedCnt = 0;
        while(finishedCnt < jobs.length){
            boolean inner = false;
            while(i<jobs.length && jobs[i][0] <= last){
                inner=true;
                PQ.add(jobs[i++]);
            }
            
            if(!PQ.isEmpty()){
                int[] job=PQ.poll();
                int requestTime = job[0];
                int duration = job[1];
                last += duration;
                ans +=  last - requestTime;
                finishedCnt++;
            }else if(i < jobs.length){
                last = jobs[i][0];
            }
        }
        return ans/jobs.length;
    }
}