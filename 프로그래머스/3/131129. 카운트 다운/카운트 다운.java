import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    int[][] scores = new int[100_001][2];
    
    public int[] solution(int target) {
        setup();
        // debug();
        
        for(int i=1;i<=60;i++){
            for(int j=61;j<=target;j++){
                if(scores[j][0] > scores[j-i][0] + scores[i][0] || 
                  (scores[j][0] == scores[j-i][0] + scores[i][0] && scores[j][1] < scores[j-i][1] + scores[i][1])){
                    scores[j][0] = scores[j-i][0] + scores[i][0];
                    scores[j][1] = scores[j-i][1] + scores[i][1];
                }
            }
        }
        
        return scores[target];
    }
    
    void setup(){
        Arrays.stream(scores).forEach(row -> row[0] = Integer.MAX_VALUE);
        for(int i=1;i<=20;i++){
            scores[i][0] = 1;
            scores[i][1] = 1;
        }
        
        // [2,2]
        scores[23] = scores[25] = scores[29] = scores[31] = scores[35] = scores[37]
            = scores[52] = scores[53] = scores[55] = scores[56] = scores[58] = scores[59] = new int[]{2,2};
        
        // [2,1]
        scores[41] = scores[43] = scores[44] = scores[46] = scores[47] = scores[49] = new int[]{2,1};
        
        // [1,1]
        scores[50] = new int[]{1,1};
        
        // [1,0]
        for(int i=20;i<=60;i++){
            if(scores[i][0] == Integer.MAX_VALUE && scores[i][1] == 0){
                scores[i][0] = 1;
            }
        }
    }
    
    void debug(){
        for(int i=1;i<=60;i++){
            out.printf("%d:[%d, %d], ", i, scores[i][0], scores[i][1]);
        }
    }
}