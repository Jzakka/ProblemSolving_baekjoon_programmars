import java.util.*;

import static java.lang.System.*;

class Solution {
    Set<Set<Integer>> combinations = new HashSet();
    
    public int solution(String[] user_id, String[] banned_id) {
        // out.println("frodo".matches("fr.d."));
        for(int i=0;i<banned_id.length;i++){
            banned_id[i] = banned_id[i].replaceAll("\\*", ".");
        }
        
        count(user_id,banned_id, 0, new HashSet());
        return combinations.size();
    }
    
    void count(String[] users, String[] banned, int i, Set<Integer> combination){
        if(i == banned.length){
            combinations.add(new HashSet(combination));
            return;
        }
        
        for(int j = 0;j<users.length;j++){
            if(users[j].matches(banned[i]) && !combination.contains(j)){
                combination.add(j);
                count(users, banned, i+1, combination);
                combination.remove(j);
            }
        }
    }
    
    void debug(int i, int j){
        for(int t=0;t<i;t++){
            out.print("\t");
        }
        out.println("i: " + i + " j: " + j);
    }
}