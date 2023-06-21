import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(long[] numbers) {
        return Arrays.stream(numbers)
            .mapToInt(number -> {
                // 이진수 변환
                List<Boolean> binary = transform(number);
            
                // 개수 판독
                int bSize=  binary.size();
                if(!isPowerOfTwo(bSize+1)){
                    int power = 1;
                    while(power < bSize+1){
                        power <<= 1;
                    }
                    int expandSize = power - bSize - 1;
                    List<Boolean> expanded = IntStream.range(0, expandSize)
                                  .mapToObj(i -> false)
                                  .collect(Collectors.toList());

                    // 왼쪽으로 0 확장
//                     List<Boolean> leftExpanded = new ArrayList();
//                     leftExpanded.addAll(expanded);
//                     leftExpanded.addAll(binary);

//                     int leftJudge = judge(leftExpanded, 0, leftExpanded.size()-1, true);
//                     if(leftJudge == 1){
//                         return 1;
//                     }
                    
                    // 오른쪽으로 0 확장
                    List<Boolean> rightExpanded = new ArrayList();
                    rightExpanded.addAll( binary);
                    rightExpanded.addAll( expanded);

                    return judge(rightExpanded, 0, rightExpanded.size()-1, true);
                }
                
                return judge(binary, 0, bSize-1, true);
            })
            .toArray();
    }
    
    void debug(List<Boolean> list){
        for(Boolean b:list){
            System.out.print(b?1:0);
        }
        System.out.println();
    }
    
    int judge(List<Boolean> binary, int start, int end, boolean parent){
        // 더미노드 자식이 리얼노드이면 0
        if(start >= end){
            return (binary.get(start) && !parent) ? 0: 1;
        }
        
        int root = (start + end)/ 2;
        
        // System.out.printf("{%n\trange { start: %d,end: %d},%n\tparent {value: %b},%n\troot {idx: %d,value: %b}%n}%n", start, end, parent, root, binary.get(root));
        
        if(!parent && binary.get(root)){
            return 0;
        }
        
        return judge(binary, start, root - 1, binary.get(root)) * judge(binary, root+1, end, binary.get(root));
    }
    
    boolean isPowerOfTwo(int num){
        return (num & (num-1)) == 0;
    }
    
    List<Boolean> transform(long number){
        List<Boolean> binary = new ArrayList();
        
        while(number > 0){
            if((number & 1) == 1){
                binary.add(true);
            }else{
                binary.add(false);
            }
            number >>= 1;
        }
            
        return binary;
    }
}