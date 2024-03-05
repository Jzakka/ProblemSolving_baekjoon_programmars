import java.util.*;

class Solution {
    TreeMap<Integer, Long> PQ = new TreeMap();
    
    public int[] solution(String[] operations) {
        for(String ops : operations){
            String[] args = ops.split("\\s+");
            String action = args[0];
            int number = Integer.parseInt(args[1]);
            if(action.equals("I")){
                insert(number);
            }else if(number == 1){
                delMax();
            }else{
                delMin();
            }
        }
        
        if(PQ.isEmpty()){
            return new int[] {0, 0};
        }
        return new int[] {PQ.lastKey(), PQ.firstKey()};
    }
    
    void insert(int num){
        if(!PQ.containsKey(num)){
            PQ.put(num, 0L);
        }
        PQ.put(num, PQ.get(num) + 1);
    }
    
    void delMax(){
        if(PQ.isEmpty()){
            return;
        }
        delete(PQ.lastKey());
    }
    
    void delMin(){
        if(PQ.isEmpty()){
            return;
        }
        delete(PQ.firstKey());
    }
    
    void delete(int num){
        if(PQ.get(num) == 1){
            PQ.remove(num);
            return;
        }
        PQ.put(num, PQ.get(num) - 1);
    }
}