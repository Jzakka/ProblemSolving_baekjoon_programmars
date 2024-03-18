import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    final double LOG2=Math.log(2);
    public int[] solution(long[] numbers) {
        int[] ans = new int[numbers.length];
        
        
        for(int i=0;i<numbers.length;i++){
            ans[i] = solution(numbers[i]);
        }
        
        
        return ans;
    }
    
    int solution(long num){
        if(num==1){
            return 1;
        }
        String binString = Long.toBinaryString(num);
        int digit = binString.length();
        
        while(Math.pow(2,(int)(Math.log(digit+1)/LOG2)) != digit+1){
            digit++;
        }
        
        String bin = String.format("%"+digit+"s", binString).replace(" ", "0");
        
        // out.println("digit:"+digit+", bin:" + bin);
        
        return check(bin, 0, bin.length()-1, '1') ? 1:0;
    }
    
    boolean check(String bin, int s, int e, char prev){
        int m = (s+e)/2;
        char current = bin.charAt(m);
        if(current=='1' && prev=='0'){
            return false;
        }
        if(s>=e){
            return true;
        }
        
        return check(bin,s,m-1, current) && check(bin,m+1,e, current);
    }
}