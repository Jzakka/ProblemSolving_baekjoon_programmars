import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    int n,m;
    int[] bits;
    public int solution(int[][] beginning, int[][] target) {
        n = target.length;
        m = target[0].length;
        bits = makeBits(beginning, target);
        
        int cnt1 = 0;
        int cnt2 = 0;
        for(int i=0;i<n;i++){
            if((bits[i]&1) == 1){
                cnt1++;
                bits[i] = ~bits[i];
            }else{
                cnt2++;
            }
            // debug();
        }
        for(int j=0;j<m;j++){
            if(((bits[0] >> j) & 1) == 1){
                cnt1++;
                for(int i=0;i<n;i++){
                    bits[i] ^= (1 << j);
                }
            }else{
                cnt2++;
            }
            // debug();
        }
        if(allZero(bits)){
            return Math.min(cnt1,cnt2);
        }
        
        return -1;
    }
    
    int[] makeBits(int[][] a, int[][] b){
        int[] bits = new int[n];
        for(int i=0;i<n;i++){
            int val = 0;
            for(int j=0;j<m;j++){
                if(a[i][j] != b[i][j]){
                    val += (1 << j);
                }
            }
            bits[i] = val;
        }
        
        return bits;
    }
    
    void debug(){
        out.println("===DEBUG===");
        for(int bit:bits){
            String row = String.format("%"+m+"s%n", Integer.toBinaryString(bit))
                .replace(" ", "0");
            out.printf(row);
        }
    }
    
    boolean allZero(int[] mat){
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(((mat[i] >> j)&1) == 1){
                    return false;
                }
            }
        }
        return true;
    }
}