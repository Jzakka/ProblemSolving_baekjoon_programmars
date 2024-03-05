import java.util.*;
import java.util.stream.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> workQueue = new PriorityQueue<>((a, b)->b-a);
        workQueue.addAll(Arrays.stream(works).boxed().collect(Collectors.toList()));
        
        while(n-- > 0){
            int heaviest =  workQueue.poll();
            if(heaviest == 0){
                return 0;
            }
            
            workQueue.add(heaviest-1);
        }
        
        return workQueue.stream()
            .mapToLong(i -> i*i)
            .sum();
    }
}