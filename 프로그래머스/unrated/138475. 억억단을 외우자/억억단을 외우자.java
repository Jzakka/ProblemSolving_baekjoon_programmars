import java.util.*;

class Solution {
    int[] counts;
    
    public int[] solution(int e, int[] starts) {
        counts = new int[e+1];
        counts[0] = 0;
        counts[1] = 1;
        
        countDiv(e);
        
        int left = Arrays.stream(starts).min().getAsInt();
        
        int[] accMaxIdx = new int[e+1];
        
        int max = 0;
        int maxIdx = -1;
        for(int i = e;i>=left;i--){
            int cnt = counts[i];
            if(max <= cnt){
                maxIdx = i;
                max = cnt;
            }
            
            accMaxIdx[i] = maxIdx;
        }
        
        // debugCounts(e);
        
        return Arrays.stream(starts).map(s->accMaxIdx[s]).toArray();
    }
    
    void countDiv(int e){
        for(int i = 1;i<=e;i++){
            for(int j = i;j<=e;j+=i){
                counts[j]++;
            }
        }
    }
    
    
    void debugCounts(int e){
        for(int i=1;i<=e;i++){
            System.out.printf("%2d|", i);    
        }
        System.out.println();
        for(int i=1;i<=e;i++){
            System.out.printf("%2d|", counts[i]);    
        }
        System.out.println();
    }
}

/**
1 2 3 4 5 6 7 8
    3 4 5 6 7 8
            7 8
            

*/