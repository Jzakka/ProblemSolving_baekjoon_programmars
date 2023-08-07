import java.util.*;

class Solution {
    int n,m;
    Deque<Deque<Integer>> body;
    Deque<Integer> left;
    Deque<Integer> right;
    public int[][] solution(int[][] rc, String[] operations) {
        n = rc.length;
        m = rc[0].length;
        
        body = new ArrayDeque();
        left = new ArrayDeque();
        right = new ArrayDeque();
        
        for(int i=0;i<n;i++){
            Deque<Integer> segment = new ArrayDeque();
            body.offerLast(segment);
            
            left.offerLast(rc[i][0]);
            right.offerLast(rc[i][m-1]);
            
            for(int j = 1;j<m-1;j++){
                segment.offerLast(rc[i][j]);
            }
        }
        
        for(String op:operations){
            if(op.equals("Rotate")){
                rotate();
            }else{
                shiftRow();
            }
        }
        
        int i = 0;
        Iterator<Integer> leftIt = left.iterator();
        while(leftIt.hasNext()){
            rc[i++][0] = leftIt.next();
        }
        i = 0;
        Iterator<Integer> rightIt = right.iterator();
        while(rightIt.hasNext()){
            rc[i++][m-1] = rightIt.next();
        }
        
        i = 0;
        Iterator<Deque<Integer>> bodyIt = body.iterator();
        while(bodyIt.hasNext()){
            Deque<Integer> segment = bodyIt.next();
            Iterator<Integer> segIt = segment.iterator();
            int j = 1;
            while(segIt.hasNext()){
                rc[i][j++] = segIt.next();
            }
            i++;
        }
        
        return rc;
    }
    
    void rotate(){
        body.getFirst().offerFirst(left.pollFirst());
        right.offerFirst(body.getFirst().pollLast());
        body.getLast().offerLast(right.pollLast());
        left.offerLast(body.getLast().pollFirst());
    }
    
    void shiftRow(){
        left.offerFirst(left.pollLast());
        body.offerFirst(body.pollLast());
        right.offerFirst(right.pollLast());
    }
}