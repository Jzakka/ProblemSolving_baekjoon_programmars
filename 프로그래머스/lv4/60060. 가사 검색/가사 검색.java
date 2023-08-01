import java.util.*;

class Solution {
    class Trie {
        Map<Integer, Integer> wordLens = new HashMap();
        Trie[] nexts = new Trie[26];
        
        Trie getNext(char letter){
            if(!hasNext(letter)){
                nexts[letter-'a'] = new Trie();
            }
            return nexts[letter-'a'];
        }
        
        boolean hasNext(char letter){
            return nexts[letter-'a'] != null;
        }
        
        void increaseLens(int len){
            if(!wordLens.containsKey(len)){
                wordLens.put(len, 1);
                return;
            }
            wordLens.put(len, wordLens.get(len)+1);
        }
        
        int getLen(int len){
            if(!wordLens.containsKey(len)){
                return 0;
            }
            return wordLens.get(len);
        }
    }
    
    public int[] solution(String[] words, String[] queries) {
        Trie root = new Trie();
        Trie rRoot = new Trie();
        
        for(String word:words){
            build(root, word);
            build(rRoot, new StringBuilder(word).reverse().toString());
        }
        
        int[] res= new int[queries.length];
        
        int i = 0;
        for(String query:queries){
            boolean dir = getDirection(query);
            if(dir){
                // System.out.printf("FORWARD %s%n", query);
                res[i++] = match(root, query, 0);
            }else{
                // System.out.printf("BACKWARD %s%n", query);
                res[i++] = match(rRoot, new StringBuilder(query).reverse().toString(), 0);
            }
        }
        
        return res;
    }
    
    int match(Trie cursor, String query, int idx){
        char letter = query.charAt(idx);
        // System.out.printf("%sIDX:%d, LETTER:%s%n", " ".repeat(idx), idx, letter);
        
        if(letter == '?'){
            return cursor.getLen(query.length());
        }
        
        if(cursor.hasNext(letter)){
            return match(cursor.getNext(letter), query, idx + 1);
        }
        return 0;
    }
    
    boolean getDirection(String query){
        return query.charAt(query.length() - 1) == '?';
    }
    
    
    void build(Trie cursor, String word){
        int wLen = word.length();
        cursor.increaseLens(wLen);
        for(int i = 0; i< wLen;i++){
            char letter = word.charAt(i);
            cursor = cursor.getNext(letter);
            cursor.increaseLens(wLen);
        }
    }
}