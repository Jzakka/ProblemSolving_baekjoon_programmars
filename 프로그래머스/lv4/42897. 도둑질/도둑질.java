import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[] money) {
        int n = money.length;
        
        int[] DP1 = new int[n];
        int[] DP2 = new int[n];
        Arrays.fill(DP1, Integer.MIN_VALUE);
        Arrays.fill(DP2, Integer.MIN_VALUE);
        DP1[0] = money[0];
        DP2[1] = money[1];
        DP1[2] = money[2];
        DP2[2] = money[2];
        
        int maxVal = 0;
        for(int i=0;i<n-1;i++){
            maxVal = Math.max(maxVal, DP1[i]);
            if(i+2 < n-1){
                DP1[i+2] = Math.max(DP1[i+2], DP1[i] + money[i+2]);
                if(i+3 < n-1){
                    DP1[i+3] = Math.max(DP1[i+3], DP1[i] + money[i+3]);
                }
            }
        }
        
        for(int i=1;i<n;i++){
            maxVal = Math.max(maxVal, DP2[i]);
            if(i+2 < n){
                DP2[i+2] = Math.max(DP2[i+2], DP2[i] + money[i+2]);
                if(i+3 < n){
                    DP2[i+3] = Math.max(DP2[i+3], DP2[i] + money[i+3]);
                }
            }
        }
        
        return maxVal;
    }
}