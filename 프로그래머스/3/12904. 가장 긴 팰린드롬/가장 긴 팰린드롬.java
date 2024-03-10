import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution
{
    public int solution(String s)
    {
        int c = 0;
        int r = 0;
        
        String extended = extend(s);
        
        int[] LPS = new int[extended.length()];
        
        for(int i=0;i<LPS.length;i++){
            if(i<=r){
                LPS[i] = Math.min(LPS[2*c-i], r - i);
            }
            
            while(i - LPS[i] - 1 >= 0 && i + LPS[i] + 1 < extended.length() && 
                    extended.charAt(i - LPS[i] - 1) == extended.charAt(i + LPS[i] + 1)){
                LPS[i]++;
            }
            
            if(LPS[i] + i > r){
                r = LPS[i] + i;
                c = i;
            }
        }
        
        return Arrays.stream(LPS).max().orElse(0);
    }
    
    String extend(String s){
        StringBuilder sb = new StringBuilder("#");
        for(int i=0;i<s.length();i++){
            sb.append(s.charAt(i)).append("#");
        }
        return sb.toString();
    }
}