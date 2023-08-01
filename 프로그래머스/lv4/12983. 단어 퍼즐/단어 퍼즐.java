import java.util.*;

class Solution {
    LinkedHashMap<Integer, Set<String>> parts = new LinkedHashMap<Integer, Set<String>>();
    
    public int solution(String[] strs, String t) {
        int[] DP = new int[t.length()];
        Arrays.fill(DP, Integer.MAX_VALUE);
        for(String str:strs){
            mapAdd(str.length(), str);
        }
        
        for(int i = 0;i<t.length();i++){
            for(int len : parts.keySet()){
                if(i+1-len>=0 && parts.get(len).contains(t.substring(i + 1 - len, i + 1))){
                    if(i - len == -1){
                        DP[i] = 1;
                    }else if(DP[i-len] != Integer.MAX_VALUE){
                        DP[i] = Math.min(DP[i], DP[i - len] + 1);    
                    }
                }
            }
        }
        
        return DP[t.length()-1] == Integer.MAX_VALUE ? -1 : DP[t.length() -1];
    }
    
    void mapAdd(int key, String str){
        if(!parts.containsKey(key)){
            parts.put(key, new HashSet());
        }
        parts.get(key).add(str);
    }
}