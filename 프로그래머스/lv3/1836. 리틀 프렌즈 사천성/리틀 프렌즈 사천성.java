import java.util.*;
import java.util.stream.*;

class Solution {
    int[][][] cardPos = new int[26][2][2];// cardPos[i][0] = {첫번째 i번 카드의 x좌표, 첫번째 i번 카드의 y좌표}
    Set<Character> cards = new LinkedHashSet();
    char[][] board;
    int m,n;
    int[] dx = {-1,0,0,1};
    int[] dy = {0,-1,1,0};
    
    final int N = 0;
    final int W = 1;
    final int E = 2;
    final int S = 3;
    
    public String solution(int m, int n, String[] board) {
        try{
            init(m,n,board);    
        }catch(Exception e){
            e.printStackTrace();
            return "!";
        }
        
        
        
        List<Character> removed =new ArrayList();
        
        while(remove(removed));
             
        // debug(removed);
        
        // 결과 처리
        if(cards.size() > 0){
            return "IMPOSSIBLE";    
        }
        
        return removed.stream().map(c->c.toString()).collect(Collectors.joining());
    }
    
    boolean remove(List<Character> removed){
        for(Character card:cards){
            // System.out.println("JUDGE:" + card);
            if(reachable(card)){
                removed.add(card);
                cards.remove(card);
                return true;
            }
        }
        
        return false;
    }
    
    boolean reachable(Character card){
        int srcX = cardPos[card-'A'][0][0];
        int srcY = cardPos[card-'A'][0][1];
        int destX = cardPos[card-'A'][1][0];
        int destY = cardPos[card-'A'][1][1];
        
        for(int i =0;i<4;i++){
            int nextX = srcX + dx[i];
            int nextY = srcY + dy[i];
            
            if(available(card, nextX, nextY) 
               && reachableUnder1Turn(card, nextX, nextY, destX, destY, 0, i)){
                board[srcX][srcY] = '.';
                board[destX][destY] = '.';
                return true;
            }
        }
        
        return false;
    }
    
   boolean reachableUnder1Turn(Character card,
                               int curX,int curY,
                               int destX,int  destY, 
                               int turnCnt, int dir){
       if(curX == destX && curY == destY){
           return true;
       }
       
       if(turnCnt == 1){ // 이미 한번 꺾으면 무조건 직진
           int nextX = curX + dx[dir];
           int nextY = curY + dy[dir];
           
           return available(card, nextX, nextY)
               && reachableUnder1Turn(card, nextX, nextY, destX, destY, 1, dir);
       }
       // 아직 안꺾으면 세가지 선택지(직진, 왼꺾, 오꺾)
       for(int i=0;i<4;i++){
           if(i == 3-dir){ // 현재 방향의 반대방향은 갈 필요 없음
               continue;
           }
           int nextX = curX + dx[i];
           int nextY = curY + dy[i];
           
           if(available(card, nextX, nextY)
               && reachableUnder1Turn(card, nextX, nextY, destX, destY, dir==i?0:1, i)){
               return true;
           }
       }
       
       return false;
   }
    
    boolean available(Character card, int nextX, int nextY){
        return nextX >= 0 && nextX < m
           && nextY >= 0 && nextY < n
           && (board[nextX][nextY] == '.' || board[nextX][nextY] == card);
    }
    
    void init(int m, int n, String[] board){
        // init
        this.board = new char[m][n];
        this.m = m;
        this.n = n;
            
        for(int i=0;i<26;i++){
            cardPos[i][0][0] = cardPos[i][0][1] =
                cardPos[i][1][0] = cardPos[i][1][1] =-1;
        }
        
        List<Character> cardList = new ArrayList();
        for(int i=0;i<m;i++){
            for(int j =0;j<n;j++){
                char cell = board[i].charAt(j);
                if(cell >= 'A' && cell <= 'Z'){
                    int firstOrSecond = cardPos[cell-'A'][0][0] == -1 ? 0:1;
                    cardPos[cell-'A'][firstOrSecond][0] = i;
                    cardPos[cell-'A'][firstOrSecond][1] = j;
                    cardList.add(cell);
                }
                this.board[i][j] = cell;
            }
        }        
        
        cards.addAll(cardList.stream().sorted().collect(Collectors.toList()));
    }
    
    void debug(List<Character>[] removed){
        int lv = 0;
        while(lv < 26 && removed[lv] != null){
            System.out.print(lv + ": ");
            for(Character c : removed[lv]){
                System.out.print(c + ", ");
            }
            System.out.println();
            lv++;
        }
    }
}