import java.util.*;
import java.util.stream.*;
import static java.lang.System.*;
class Solution {
    int[][] board;
    int[][] deltas;
    
    public int solution(int[][] board, int[][] skills) {
        this.board = board;
        deltas = new int[board.length][board[0].length];
        
        for(int[] skill: skills){
            int type = skill[0];
            if(type==1){
                attack(skill[1],skill[2],skill[3],skill[4],skill[5]);    
            }else{
                heal(skill[1],skill[2],skill[3],skill[4],skill[5]);
            }
        }
        
        postProcess();
        
        // debug();
        
        return (int)Arrays.stream(board).flatMapToInt(Arrays::stream)
            .filter(i->i>0)
            .count();
    }
    
    void attack(int r1,int c1,int  r2,int c2,int degree){
        rangeOp(r1,c1,r2,c2, -degree);
    }
    
    void heal(int r1,int c1,int r2,int c2,int degree){
        rangeOp(r1,c1,r2,c2, degree);
    }
    
    void rangeOp(int r1,int c1,int r2,int c2,int delta){
        // out.printf("r1:%d, c1:%d, r2:%d, c2:%d, delta:%d%n", r1,c1,r2,c2,delta);
        
        deltas[r1][c1] +=delta;
        if(inRange(r1,c2 + 1)){
            deltas[r1][c2+1] -=delta;
        }
        if(inRange(r2+1,c1)){
            deltas[r2+1][c1] -=delta;
        }
        if(inRange(r2+1,c2+1)){
            deltas[r2+1][c2+1] += delta;
        }
        
        // debug();
    }
    
    boolean inRange(int x, int y){
        return 0<= x&&x<board.length && 0<= y&&y<board[0].length;
    }
    
    void postProcess(){
        int n = board.length;
        int m = board[0].length;
        for(int i=0;i<n;i++){
            int sum = 0;
            for(int j=0;j<m;j++){
                sum += deltas[i][j];
                deltas[i][j] = sum;
            }
        }
        
        for(int j=0;j<m;j++){
            int sum = 0;
            for(int i = 0;i<n;i++){
                sum += deltas[i][j];
                deltas[i][j] = sum;
            }
        }
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                board[i][j] += deltas[i][j];
            }
        }
    }
    
    void debug(){
        out.println("==debug==");
        for(int[] row: deltas){
            for(int e:row){
                out.print(e + " ");
            }
            out.println();
        }
    }
}