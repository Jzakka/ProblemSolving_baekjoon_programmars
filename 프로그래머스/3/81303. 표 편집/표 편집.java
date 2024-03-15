import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    TreeSet<Integer> table;
    Deque<Integer> stk = new ArrayDeque();
    boolean[] deleted;
    int cursor;
    public String solution(int n, int k, String[] cmd) {
        deleted = new boolean[n];
        table = IntStream.range(0,n).boxed().collect(Collectors.toCollection(TreeSet::new));
        cursor = k;
        
        for(String c:cmd){
            char action = c.charAt(0);
            switch(action){
                case 'U':
                    go(-Integer.parseInt(c.substring(2)));
                    break;
                case 'D':
                    go(Integer.parseInt(c.substring(2)));
                    break;
                case 'C':
                    delete();
                    break;
                case 'Z':
                    undo();
                    break;
            }
        }
        
        StringBuilder ans = new StringBuilder();
        for(int i=0;i<n;i++){
            ans.append(deleted[i] ? "X" : "O");
        }
        return ans.toString();
    }
    
    void go(int x){
        Integer next;
        if(x < 0){
            x *= -1;
            while(x > 0 && (next=table.lower(cursor)) != null){
                cursor = next;
                x--;
            }
        }else {
            while(x > 0 && (next=table.higher(cursor)) != null){
                cursor = next;
                x--;
            }
        }
    }
    
    void delete(){
        int toDelete = cursor;
        if(cursor == table.last()){
            cursor = table.lower(cursor);
        }else{
            cursor = table.higher(cursor);
        }
        table.remove(toDelete);
        deleted[toDelete] = true;
        stk.offerLast(toDelete);
    }
    
    void undo(){
        int toRestore = stk.pollLast();
        deleted[toRestore] = false;
        table.add(toRestore);
    }
}