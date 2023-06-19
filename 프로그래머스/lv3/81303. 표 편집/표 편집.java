import java.util.*;

class Solution {
    Stack<Integer> deleted = new Stack();
    TreeSet<Integer> table = new TreeSet();
    int n;
    int cursor;
    
    public String solution(int n, int k, String[] cmd) {    
        // init
        this.n = n;
        cursor = k;
        for(int i= 0;i<n;i++){
            table.add(i);
        }
        
        // do command
        for(String c:cmd){
            apply(c);
        }
        
        // convert to result
        return convertToResult();
    }
    
    void apply(String command){
        char action = command.charAt(0);
        if(action == 'U' || action == 'D'){
            move(action, command.substring(2));
        }else if(action == 'C'){
            delete();
        }else{
            restore();
        }
        // System.out.println("COMMAND: " + command +", CURSOR: "+cursor +","+convertToResult());
    }
    
    void move(char action, String degree){
        int steps = Integer.parseInt(degree);
    
    if (action == 'U') {
        for(int i = 0; i < steps; i++) {
            Integer prev = table.lower(cursor);
            if (prev != null) {
                cursor = prev;
            }
        }
    } else {
        for(int i = 0; i < steps; i++) {
            Integer next = table.higher(cursor);
            if (next != null) {
                cursor = next;
            }
        }
    }
    }
    
    void delete(){
        Integer nextCursor = table.higher(cursor);
        table.remove(cursor);
        deleted.push(cursor);
        if(nextCursor == null){
            nextCursor = table.last();
        }
        cursor = nextCursor;
    }
    
    void restore(){
        int recentDeleted = deleted.pop();
        table.add(recentDeleted);
    }
    
    String convertToResult(){
        char[] charSec = new char[n];
        
        Arrays.fill(charSec, 'O');
        while(!deleted.isEmpty()){
            charSec[deleted.pop()] = 'X';
        }
        
        return new String(charSec);
    }
}


/*
4 x
2 v

0
1
3
5
6
7
*/