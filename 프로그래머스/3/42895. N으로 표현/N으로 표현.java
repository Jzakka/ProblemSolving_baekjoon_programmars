import java.util.*;
import java.util.stream.*;
import java.util.function.*;

import static java.lang.System.*;

class Solution {
     long N;
    Set<Long>[] results = IntStream.range(0, 9).mapToObj(i -> new HashSet()).toArray(Set[]::new);
    List<BiFunction<Long, Long, Long>> ops = Arrays.asList(
        (acc, num) -> acc + num,
        (acc, num) -> acc - num,
        (acc, num) -> acc * num,
        (acc, num) -> acc / num
    );
    public int solution(final int N, int number) {
        this.N= N;
        
        for(int i=1;i<=8;i++){
            long repeated = Long.parseLong("1".repeat(i)) * N;
            
            results[i].add(repeated);
            for(long left:results[i]){
                for(int j=1;j<=8;j++){
                    if(i + j > 8) { continue; }
                    for(long right:results[j]){
                        
                        for(BiFunction<Long,Long,Long> op:ops){
                            if(op.equals(ops.get(3)) && right == 0){ continue; }
                        
                            long result = op.apply(left, right);
                        
                            results[i + j].add(result);
                        }    
                    }
                }                
            }
        }
        
        return IntStream.rangeClosed(1,8)
            .filter(i -> results[i].contains((long)number))
            // .peek(out::println)
            .min()
            .orElse(-1);
    }
}

/*
55 + 5 / 5 - 555 + (5+5/5- (5+55))

a개로 만들수 있는 거 * b개로 만들 수 있는 거 -> (a+b)개로 만들 수 있는거

2개로 만들 수 있는 거
5 + 5
5 - 5
5 * 5
5 / 5

3개로 .. = 1개 * 1개 *1개 || 1개로 * 2개로 || 2개 * 1개
*/