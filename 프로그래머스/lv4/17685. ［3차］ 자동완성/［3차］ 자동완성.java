
class Solution {
    class Trie{
        boolean isEnd = false;
        int appearance = 0;
        Trie[] next = new Trie[26];
        
        Trie goNext(char letter){
            if(next[letter-'a'] == null){
                next[letter - 'a'] = new Trie();
            }
            return next[letter-'a'];
        }
    }
    
    
    public int solution(String[] words) {
        Trie root = new Trie();
        for(String word:words){
            Trie cur = root;
            for(int i=0;i<word.length();i++){
                char letter = word.charAt(i);
                cur = cur.goNext(letter);
                cur.appearance++;
            }
            cur.isEnd = true;
        }
        
        int res = 0;
        for(String word:words){
            Trie cur = root;
            int inputCnt = 0;
            for(;inputCnt<word.length();inputCnt++){
                char letter = word.charAt(inputCnt);
                cur = cur.goNext(letter);
                if(cur.appearance == 1){
                    inputCnt++;
                    break;
                }
            }
            res += inputCnt;
        }
        
        return res;
    }
}