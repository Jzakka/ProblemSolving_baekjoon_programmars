import java.util.*;
import static java.lang.System.*;

class Solution {
    Deque<Integer> DQ = new ArrayDeque();
    public int solution(int[] stones, int k) {
        int cursor=0;
        for(;cursor<k;cursor++){
            addDeque(stones[cursor]);
        }
        
        int ans = Integer.MAX_VALUE;
        while(cursor <= stones.length){
            int leader = DQ.getFirst();
            ans = Math.min(ans, leader);
            
            if(stones[cursor-k] == leader){
                DQ.pollFirst();
            }
            
            if(cursor < stones.length){
                addDeque(stones[cursor]);    
            }
            cursor++;
        }
        
        return ans;
    }
    
    void addDeque(int num){
        while(!DQ.isEmpty() && DQ.getLast() < num){
            DQ.pollLast();
        }
        DQ.offerLast(num);
    }
}