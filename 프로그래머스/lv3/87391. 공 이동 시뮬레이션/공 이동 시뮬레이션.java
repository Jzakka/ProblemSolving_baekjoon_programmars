class Solution {
    int n;
    int m;
    
    public long solution(int n, int m, int x, int y, int[][] queries) {
        this.n = n;
        this.m = m;
        
        long startY = 0;
        long endY = n-1;
        long startX = 0;
        long endX = m-1;
        
        long movedStartY = 0;
        long movedEndY = m-1;
        long movedStartX = 0;
        long movedEndX = n-1;
        
        long[] yInfo = {0, 0, 0}; // 원점으로부터 거리, 최소 위치, 최대 위치
        long[] xInfo = {0, 0, 0}; // 원점으로부터 거리, 최소 위치, 최대 위치
        
        for(int[] query : queries){
            if(query[0] < 2){ // y방향으로 움직이기(가로)
                setYInfo(yInfo, query);
                movedStartY  = move(movedStartY, query, m);
                movedEndY = move(movedEndY, query, m);
            }else{              // x방향으로 움지기이기(세로)
                setXInfo(xInfo, query);
                movedStartX  = move(movedStartX, query, n);
                movedEndX = move(movedEndX, query, n);
            }
        }
        
        // ===디버그===
        
//         System.out.println("===Y정보===");
//         debug(yInfo, movedStartY, movedEndY);
        
//         System.out.println("===X정보===");
//         debug(xInfo, movedStartX, movedEndX);
        
        // ==========
        
        long yCnt = getYCnt(yInfo, movedStartY, movedEndY, y);
        long xCnt = getXCnt(xInfo, movedStartX, movedEndX, x);
        
        return xCnt*yCnt;
    }
    
    void setYInfo(long[] yInfo, int[] query){
        if(query[0] == 0){
            yInfo[0] -= query[1];
        }else{
            yInfo[0] += query[1];
        }
        yInfo[1] = Math.min(yInfo[1], yInfo[0]);
        yInfo[2] = Math.max(yInfo[2], yInfo[0]);
    }
    
    void setXInfo(long[] xInfo, int[] query){
        if(query[0] == 2){
            xInfo[0] -= query[1];
        }else{
            xInfo[0] += query[1];
        }
        xInfo[1] = Math.min(xInfo[1], xInfo[0]);
        xInfo[2] = Math.max(xInfo[2], xInfo[0]);
    }
    
    long move(long pos, int[] query, int limit){
        if((query[0]&1) == 0){
            pos -= query[1];
            if(pos < 0){
                pos = 0;
            }
        }else{
            pos += query[1];
            if(pos >= limit){
                pos = limit-1;
            }
        }
        return pos;
    }
    
    long getYCnt(long[] yInfo, long movedStartY, long movedEndY, int y){
        long yCnt = 0;
        
        // 최종 Y좌표가 목표 Y좌표인 것들의 개수 찾기
        long yMoved = yInfo[0];
        long yMin = Math.abs(yInfo[1]);
        long yMax = yInfo[2];
        
        // 시작단과 최종 Y좌표가 같은 것들의 개수는 yMin의 개수 + 1 ==> group A
        long startBlocked = Math.min(yMin+1, m);
        // 끝단과 최종 Y좌표가 같은 것들의 개수는 yMax의 개수 + 1 - (A그룹에도 속하는 녀석들 개수)
        long endBlocked = yMax + 1;
        if(startBlocked + endBlocked >= m){
            endBlocked = m - startBlocked;
        }
        
        // System.out.println("Y정보");
        // System.out.printf("START_BLOCKED:%d%n", startBlocked);
        // System.out.printf("END_BLOCKED:%d%n", endBlocked);
        
        //시작점의 최종위치가 목표Y이면 yCnt증가
        if(movedStartY == y){
            yCnt+=startBlocked;
        }
        //끝점의 최종위치가 목표Y이면 yCnt증가
        if(movedEndY == y){
            yCnt+=endBlocked;
        }
        // startBlocked, endBlocked에 모두 속하지 않는 원소가 있고 yCnt가 증가하지 않았다면 답은 유일하게 존재
        if(startBlocked + endBlocked < m-1 && yCnt == 0){
            yCnt=1;
        }
        
        // System.out.printf("Y_CNT:%d%n", yCnt);
        return yCnt;
    }
    
    long getXCnt(long[] xInfo, long movedStartX, long movedEndX, int x){
        long xCnt = 0;
        
        // 최종 X좌표가 목표 X좌표인 것들의 개수 찾기
        long xMoved = xInfo[0];
        long xMin = Math.abs(xInfo[1]);
        long xMax = xInfo[2];
        
        // 시작단과 최종 X좌표가 같은 것들의 개수는 xMin의 개수 + 1 ==> group A
        long startBlocked = Math.min(xMin+1, n);
        // 끝단과 최종 X좌표가 같은 것들의 개수는 xMax의 개수 + 1 - (A그룹에도 속하는 녀석들 개수)
        long endBlocked = xMax + 1;
        if(startBlocked + endBlocked >= n){
            endBlocked = n - startBlocked;
        }
        
        // System.out.println("X정보");
        // System.out.printf("START_BLOCKED:%d%n", startBlocked);
        // System.out.printf("END_BLOCKED:%d%n", endBlocked);
        
        //시작점의 최종위치가 목표X이면 xCnt증가
        if(movedStartX == x){
            xCnt+=startBlocked;
        }
        //끝점의 최종위치가 목표Y이면 yCnt증가
        if(movedEndX == x){
            xCnt+=endBlocked;
        }
        // startBlocked, endBlocked에 모두 속하지 않는 원소가 있고 xCnt가 증가하지 않았다면 답은 유일하게 존재
        if(startBlocked + endBlocked < n - 1 && xCnt == 0){
            xCnt = 1;
        }
        
        // System.out.printf("X_CNT:%d%n", xCnt);
        return xCnt;
    }
    
    void debug(long[] Info, long movedStart, long movedEnd){
        System.out.println("원점으로부터의 거리: " + Info[0]);
        System.out.println("최소 위치: " + Info[1]);
        System.out.println("최대 위치: " + Info[2]);
        System.out.println("\t시작점의 최종 좌표: " + movedStart);
        System.out.println("\t끝점의 최종 좌표: " + movedEnd);        
    }
}

/*

yMin = 2
yMax = 5

3

 0      1         2       3      4       5
      n-yMAx     ymin
 
        -1      1        -2      3       -1
원점
으로부터  -1      0        -2      1        0
거라
min     -1     -1        -2     -2        -2
max      0      0         0      1        1

1,2 는 모두 0의 이동거리와 같고
2는 3의 이동거리와 같다?

0 1 2 3 4 5 

        -1  2  -2   1     -1
원점으로
부터의    -1  1  -1   0     -1
거리
min     -1  -1 -1  -1     -1
max      0   1  1   1      1

0,1 -> 0의 최종위치와 같음 = 0

2 = 1
3 = 2

4,5 -> 5의 최종위치와 같음 = 3

 
일단 시작점이 움직여서 말단에 닿는 순간 최종 좌표는 말단의 최종좌표와 같아지게 된다.


*/