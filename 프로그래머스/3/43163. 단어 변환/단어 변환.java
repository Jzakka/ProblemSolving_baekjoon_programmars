import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        Queue<String> Q = new LinkedList();
        Set<String> visited = new HashSet();
        Q.add(begin);
        visited.add(begin);
        
        int ans = 0;
        while(!Q.isEmpty()){
            int qLen = Q.size();
            for(int i=0;i<qLen;i++){
                String curWord = Q.poll();    
                
                if(curWord.equals(target)){
                    return ans;
                }
                
                for(String word : words){
                    if(!visited.contains(word) && adjacent(curWord, word)){
                        visited.add(word);
                        Q.add(word);
                    }
                }
            }
            ans++;
        }
        
        return 0;
    }
    
    boolean adjacent(String a, String b){
        int diffCnt = 0;
        for(int i=0;i<Math.min(a.length(), b.length());i++){
            if(a.charAt(i) != b.charAt(i) && ++diffCnt > 1){
                return false;
            }
        }
        return true;
    }
}