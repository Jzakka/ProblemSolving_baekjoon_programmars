import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    int[] seq;
    Map<String,String> map = new HashMap();
    public String[] solution(String[] s) {
        String[] ans = new String[s.length];
        
        for(int i=0;i<s.length;i++){
            ans[i] = map.computeIfAbsent(s[i], str -> solution(str));
        }
        
        return ans;
    }
    
    String solution(String s){
        StringBuilder ans = new StringBuilder();
        int cnt = 0;
        
        int idx;
        for(int i=0;i<s.length();i++){
            ans.append(s.charAt(i));
            if(ans.length() >= 3 && ans.substring(ans.length()-3).equals("110")){
                ans.delete(ans.length()-3, ans.length());
                cnt++;
            }
        }
        
        // out.println(ans + " cnt:"+cnt);
        return ans.insert(ans.lastIndexOf("0") + 1, "110".repeat(cnt)).toString();
    }
}