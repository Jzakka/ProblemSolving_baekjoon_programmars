import java.util.*;

class Solution {
    Map<String, String> DP = new HashMap();
    
    public String[] solution(String[] s) {
        return Arrays.stream(s)
            .map(code -> {
                if(DP.containsKey(code)){
                    return DP.get(code);
                }
                Extract ex = extract110(code, 0);
                String result = merge(ex.remain, ex.cnt);
                DP.put(code, result);
                return result;
            })
            .toArray(String[]::new);
    }
    
    String merge(StringBuilder remain, int cnt){
        int lastIdx = remain.lastIndexOf("0");
        
        return remain
            .insert(lastIdx + 1, "110".repeat(cnt))
            .toString();
    }
    
    Extract extract110(String s, int cnt){
        StringBuilder sb = new StringBuilder();
        
        for(int i=0;i<s.length();i++){
            sb.append(s.charAt(i));
            if(sb.length() >= 3 && sb.substring(sb.length()-3).equals("110")){
                sb.delete(sb.length()-3, sb.length());
                cnt++;
            }
        }
        
        return new Extract(sb, cnt++);
    }
    
    static class Extract{
        StringBuilder remain;
        int cnt;
        
        Extract(StringBuilder r, int c){
            remain = r;
            cnt = c;
        }
    }
}