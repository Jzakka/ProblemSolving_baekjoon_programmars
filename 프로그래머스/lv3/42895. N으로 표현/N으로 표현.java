import java.util.*;

class Solution {
    public int solution(int N, int number) {
        if(number == N){
            return 1;
        }
        Set<Integer>[] results = new Set[9];
        for(int i = 0;i<9;i++){
            results[i] = new LinkedHashSet();
        }
        results[0].add(0);
        results[1].add(N);
        
        for(int i = 2;i <= 8;i++){
            results[i].add(Integer.valueOf("1".repeat(i)) * N);
            
            for(int j = 1;j<i;j++){
                Set<Integer> lefts = results[j];
                Set<Integer> rights = results[i - j];
                
                for(int left:lefts){
                    for(int right:rights){
                        results[i].add(left + right);
                        results[i].add(left - right);
                        results[i].add(left * right);
                        if(right != 0){
                            results[i].add(left/right);
                        }
                    }
                }
            }
            if(results[i].contains(number)){
                return i;
            }
        }
        
        return -1;
    }
}
