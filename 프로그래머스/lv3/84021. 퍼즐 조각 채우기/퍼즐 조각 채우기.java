import java.util.*;
import java.util.stream.*;

class Solution {
    class Figure{
        int num;
        int[] pos = {-1,-1};
        List<int[]> components = new ArrayList(Arrays.asList(pos));
        int area;
        
        Figure(int num, int x, int y, int area){
            this.num = num;
            this.pos[0] = x;
            this.pos[1] = y;
            this.area = area;
        }
    }
    
    // 보드 방향        E W S N
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    
    final int E = 0;
    final int W = 1;
    final int S = 2;
    final int N = 3;
    
    int[][] puzzleDir = {
        {E,W,S,N},
        {S,N,W,E},
        {W,E,N,S},
        {N,S,E,W}
    };
    
    List<Figure> blanks = new ArrayList();
    Map<Integer, Set<Figure>> pieces = new HashMap();
    int[][] board;
    int[][] table;
    
    public int solution(int[][] game_board, int[][] table) {
        board = game_board;
        this.table = table;
        Set<Integer> boardVisited = new HashSet();
        Set<Integer> tableVisited = new HashSet();
        int boardNum = 0;
        int tableNum = 0;
        
        // gameBoard의 빈칸들의 위치를 저장 (빈칸 위치, 빈칸 넓이)
        for(int i=0;i<game_board.length;i++){
            for(int j = 0;j<game_board[0].length;j++){
                if(game_board[i][j] == 0 && !boardVisited.contains(cantorPair(i,j))){
                    Figure figure = new Figure(boardNum++,i,j,0);
                    build(boardVisited, i,j,figure, true);
                    blanks.add(figure);
                }
            }
        }
        
        //table에서 퍼즐 조각들을 저장 (퍼즐조각번호, 퍼즐조각 위치, 퍼즐 넓이)
        for(int i=0;i<table.length;i++){
            for(int j = 0;j<table[0].length;j++){
                if(table[i][j] == 1 && !tableVisited.contains(cantorPair(i,j))){
                    Figure figure = new Figure(tableNum++,i,j,0);
                    build(tableVisited, i,j,figure, false);
                    mapAdd(figure);
                }
            }
        }
        
        // debug();
        
        int res = 0;
        //빈칸에 대해 자기랑 넓이가 같은 녀석들중에 매치하는 퍼즐 조각이 있으면 결과++
        for(Figure blank:blanks){
            if(pieces.containsKey(blank.area)){
                for(Figure piece:pieces.get(blank.area)){
                    // System.out.printf("MATCHING %d and %d%n", blank.num, piece.num);
                    if(isMatch(blank, piece)){
                        res+=blank.area;
                        pieces.get(blank.area).remove(piece);
                        // System.out.println("MATCHED SUCCESS!");
                        break;
                    }
                    // System.out.println("MATCHED FAILED");
                }
            }
        }
        
        return res;
    }
    
    void build(Set<Integer> visited, int curX, int curY, Figure figure, boolean isBoard){
        visited.add(cantorPair(curX, curY));
        figure.components.add(new int[]{curX,curY});
        figure.area++;
        
        for(int i=0;i<4;i++){
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];
            
            if(!visited.contains(cantorPair(nextX, nextY)) && available(nextX, nextY,isBoard?board:table, isBoard)){
                build(visited, nextX, nextY, figure, isBoard);
            }
        }
    }
    
    boolean isMatch(Figure blank, Figure piece){
        for(int[] comp:blank.components){
            for(int i=0;i<4;i++){
                // System.out.printf("%d와 %d를 %d방향으로 매칭 시도%n", blank.num,piece.num,i);
                if(isMatch(comp, piece.pos, i)){
                    // System.out.printf("MATCHED AT DIR %d%n", i);
                    return true;
            }
        }
            
        }
        return false;
    }
    
    boolean isMatch(int[] board, int[] piece, int dir){
        int bX = board[0];
        int bY = board[1];
        int pX = piece[0];
        int pY = piece[1];
        
        Set<Integer> visited = new HashSet();
        return congruence(visited, bX,bY,pX,pY,dir);
    }
    
    boolean congruence(Set<Integer> visited, int x1,int y1,int x2,int y2, int dir){
        // System.out.printf("\t{%d,%d},{%d,%d}%n",x1,y1,x2,y2);
        if(!available(x2, y2, table,false)){
            return false;
        }
        
        visited.add(cantorPair(x1,y1));
        
        for(int i=0;i<4;i++){
            int nextX1 = x1 + dx[i];
            int nextY1 = y1 + dy[i];
            
            int nextX2 = x2 + dx[puzzleDir[dir][i]];
            int nextY2 = y2 + dy[puzzleDir[dir][i]];
            
            // System.out.printf("보드는 %d방향, 퍼즐은 %d방향%n", i, puzzleDir[dir][i]);
            
            if(!visited.contains(cantorPair(nextX1,nextY1))
               && available(nextX1, nextY1, board, true) 
               && !congruence(visited, nextX1, nextY1, nextX2, nextY2, dir)){
                return false;
            }
        }
        return true;
    }
    
    boolean available(int x,int y,int[][] matrix, boolean isBoard){
        return 0<=x && x<matrix.length && 0<=y && y<matrix[0].length
            && matrix[x][y] == (isBoard?0:1);
    }
    
    int cantorPair(int a,int b){
        return (a+b)*(a+b+1)/2 + b;
    }
    
    void mapAdd(Figure piece){
        if(!pieces.containsKey(piece.area)){
            pieces.put(piece.area, new HashSet());
        }
        pieces.get(piece.area).add(piece);
    }
    
    void debug(){
        System.out.println("===BLANK INFO===");
        for(Figure blank:blanks){
            System.out.printf("\t{BLANK_NUM:%d, POS:{%d,%d}, AREA:%d}%n",
                              blank.num, blank.pos[0], blank.pos[1], blank.area);
        }
        
        System.out.println("===PIECE INFO===");
        for(Integer key:pieces.keySet()){
            for(Figure piece:pieces.get(key)){
                System.out.printf("\t{PIECE_NUM:%d, POS:{%d,%d}, AREA:%d}%n",
                                  piece.num, piece.pos[0], piece.pos[1], piece.area);
            }
        }
    }
}