import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    Map<Integer, List<Integer>> indexes = new HashMap();
    int[] a;
    public int solution(int[] a) {
        this.a=a;
        for(int i=0;i<a.length;i++){
            indexes.computeIfAbsent(a[i], k->new ArrayList()).add(i);
        }
        
        return indexes.keySet().stream()
            .sorted(Comparator.comparingInt(k->indexes.get(k).size()))
            .mapToInt(k->count(k, indexes.get(k)))
            .max()
            .getAsInt() * 2;
    }
    
    int count(int k, List<Integer> indexes){
        // out.println("k:" + k + ", indexes:" + indexes);
        int cover = -1;
        int cnt = 0;
        for(int idx:indexes){
            // out.println("\tcover:" + cover);
            if(idx-1 > cover  && a[idx - 1] != k){
                cover = idx;
                cnt++;
            }else if(idx + 1 < a.length && a[idx + 1] != k){
                cover = idx + 1;
                cnt++;
            }
        }
        // out.println("cnt:" + cnt);
        return cnt;
    }
}