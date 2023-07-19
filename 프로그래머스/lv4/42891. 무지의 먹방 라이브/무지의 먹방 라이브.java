import java.util.*;
import java.util.stream.*;

class Solution {
    class Food implements Comparable<Food>{
        int num;
        int time;
        Food(int num,int time){
            this.num = num;
            this.time = time;
        }
        
        @Override
        public int compareTo(Food f){
            return time - f.time;
        }
    }
    public int solution(int[] food_times, long k) {
        PriorityQueue<Food> Q = new PriorityQueue();
        long elapsedRound = 0;
        long elapsedTime = 0;
        
        for(int i=0;i<food_times.length;i++){
            Q.add(new Food(i+1, food_times[i]));
        }
        
        while(!Q.isEmpty()){
            int qSize = Q.size();
            Food food = Q.poll();
            long consumedRound = food.time - elapsedRound;
            elapsedTime += consumedRound * qSize;
            elapsedRound += consumedRound;
            
            // System.out.printf("QSIZE:%d, CONSUME_ROUND:%d, ELAPSED_TIME:%d, ELAPSED_ROUND:%d%n", qSize, consumedRound, elapsedTime, elapsedRound);
            
            if(elapsedTime > k){
                elapsedTime -= consumedRound*qSize;
                Q.add(food);
                List<Integer> remains = Q.stream()
                    .mapToInt(f->f.num)
                    .sorted()
                    .boxed()
                    .collect(Collectors.toList());
                
                int n = (int)((k-elapsedTime)%qSize);
                return remains.get(n);
            }
        }
        
        return -1;
    }
}