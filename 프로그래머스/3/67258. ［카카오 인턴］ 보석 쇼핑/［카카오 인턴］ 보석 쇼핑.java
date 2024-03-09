import java.util.*;
import java.util.stream.*;

class Solution {
    Map<String, Integer> count = new HashMap();
    
    public int[] solution(String[] gems) {
        int gemSize = (int)Arrays.stream(gems).distinct().count();
        
        int s = 0;
        int e = 0;
        
        int[] ans = {0, 50_000_000};
        while(e < gems.length){
            mapadd(gems[e]);
            while(s<e && count.get(gems[s]) > 1){
                mapsub(gems[s]);
                s++;
            }
            
            if(count.size() == gemSize && ans[1] - ans[0] + 1> e - s + 1){
                ans[0] = s+1;
                ans[1] = e + 1;
            }
            e++;
        }
        
        return ans;
    }
    
    void mapadd(String key){
        if(count.containsKey(key)){
            count.put(key, count.get(key) + 1);
            return;
        }
        count.put(key, 1);
    }
    
    void mapsub(String key){
        count.put(key, count.get(key) - 1);
        if(count.get(key) == 0){
            count.remove(key);
        }
    }
}