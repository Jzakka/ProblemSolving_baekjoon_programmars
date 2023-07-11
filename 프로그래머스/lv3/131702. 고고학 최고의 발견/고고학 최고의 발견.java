import java.util.*;
import java.util.stream.*;

class Solution {
    static int[] dx={-1,1,0,0};
    static int[] dy={0,0,-1,1};
    int[][] matrix;
    int matSize;
    public int solution(int[][] clockHands) {
        matrix = clockHands;
        matSize = clockHands.length * clockHands[0].length;
        
        int[] topRotCnts = new int[matrix[0].length];
        
        return top(0, topRotCnts);
    }
    
    int top(int col, int[] topRotCnts){
        if(col == matrix[0].length){
            // 맨 윗줄 설정
            for(int i = 0;i<topRotCnts.length;i++){
                rotate(0,i, topRotCnts[i]);
            }
            // System.out.println("TOP_ROTATION_COUNTS");
            // for(int cnt:topRotCnts){
            //     System.out.printf("%2d ", cnt);
            // }
            // System.out.println();
            int fillCnt = fillCnts(topRotCnts.length);
            int res = fillCnt == -1 ? Integer.MAX_VALUE :( fillCnt  + Arrays.stream(topRotCnts).sum());
            // System.out.printf("\t결과:%d%n",res);
            // 맨 윗줄 복구
            for(int i = 0;i<topRotCnts.length;i++){
                rotate(0,i, -topRotCnts[i]);
            }
            return res;
        }
        
        int res = Integer.MAX_VALUE;
        for(int i=0;i<=3;i++){
            topRotCnts[col] = i;
            res = Math.min(res, top(col+1, topRotCnts));
        }
        return res;
    }
    
    void rotate(int x,int y, int degree){
        matrix[x][y] = (matrix[x][y] + degree + 4) % 4;
        for(int i=0;i<4;i++){
            int nextX = x+dx[i];
            int nextY = y+dy[i];
            if(available(nextX, nextY)){
                matrix[nextX][nextY] = (matrix[nextX][nextY] + degree + 4) % 4;
            }            
        }
    }
    
    // 자기 윗칸을 0으로 만들기 위한 메소드
    int fillCnts(int cellNum){
        if(cellNum == matSize){
            int bottomSum  = Arrays.stream(matrix[matrix.length-1]).sum();
            
            // System.out.println("FILL_FINISHED");
            // for(int[] row:matrix){
            //     for(int cell:row){
            //         System.out.printf("%2d ", cell);
            //     }
            //     System.out.println();
            // }
            // System.out.println("BOTTOM_SUM:" + bottomSum);
            
            return bottomSum == 0 ? 0:-1;
        }
        int x = cellNum / matrix[0].length;
        int y = cellNum - x * matrix[0].length;
        
        int degree = (4 - matrix[x-1][y])%4;
        if(degree > 0){
            rotate(x,y,degree);            
            // System.out.printf("%d,%d 에서 %d만큼 회전%n", x,y,degree);
        }
        int nextFillCnts = fillCnts(cellNum+1);
        if(degree > 0){
            rotate(x,y,-degree);
        }
        return nextFillCnts == -1 ? -1 : nextFillCnts + degree;
    }
    
    boolean available(int x,int y){
        return 0<=x && x<matrix.length && 0<=y && y<matrix[0].length;
    }
}