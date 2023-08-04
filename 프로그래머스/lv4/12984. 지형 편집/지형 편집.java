import java.util.*;
import java.util.stream.*;

public class Solution {
    public long solution(int[][] land, int P, int Q) {
        int n = land.length;
        int m = land[0].length;
        
        int[] heights = Arrays.stream(land)
                       .flatMapToInt(row -> Arrays.stream(row))
                       .sorted()
                       .toArray();
        
        long[][] allHeights = new long[n*m][2];
        int heightIdx = -1;
        long sum = 0; // 총 블록 개수
        long prevHeight = -1;
        
        for(int i = 0;i<heights.length;i++){
            long height = heights[i];
            if(height > prevHeight){
                allHeights[++heightIdx] = new long[]{height, 1};
            }else{
                allHeights[heightIdx][1]++;
            }
            sum += height;
            prevHeight = height;
        }

        // for(int i=0;i<heightIdx+1;i++){
        //     System.out.println(allHeights[i][0] +", " + allHeights[i][1]);
        // }
        
        long smallerCount = 0; // 높이 h 보다 낮은 기둥의 개수
        long smallerSum = 0; // 높이 h보다 낮은 기둥들의 블록 개수 총합
        long res = Long.MAX_VALUE;
        for(int i = 0;i<heightIdx+1;i++){
            long h = allHeights[i][0];
            long count = allHeights[i][1];
            long largerSum = sum - h*count - smallerSum; // 높이 h보다 높은 기둥들의 블록 개수 총합
            
            long largerCount = n*m - smallerCount - count; // 높이 h보다 높은 기둥의 개수
            
            long cost = (h*smallerCount - smallerSum)*P + (largerSum - h*largerCount)*Q;
            
            // System.out.printf("높이: %d, 개수: %d, 미만개수: %d, 초과개수: %d, 미만총합: %d, 초과총합:%d, 비용:%d%n", 
            //                  h, count, smallerCount, largerCount, smallerSum, largerSum, cost);
            
            // 다음 h를 위해 업데이트
            if(res <= cost){
                return res;
            }
            res = cost;
            smallerSum += h*count;
            smallerCount += count;
        }
        
        return res;
    }
}