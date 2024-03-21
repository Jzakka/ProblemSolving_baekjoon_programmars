import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    int n,m;
    final int IMPOSSIBLE = Integer.MIN_VALUE;
    final int ROW = 1;
    final int COL = 2;
    
    public long solution(int n, int m, int x, int y, int[][] queries) {
        this.n = n;
        this.m = m;
        
        long[] xRange = {x, x};
        long[] yRange = {y, y};
        for(int i = queries.length-1;i>=0;i--){
            switch(queries[i][0]){
                case 0:
                    move(yRange, -queries[i][1], COL);
                    break;
                case 1:
                    move(yRange, queries[i][1], COL);
                    break;
                case 2:
                    move(xRange, -queries[i][1], ROW);
                    break;
                default: // 3
                    move(xRange, queries[i][1], ROW);
                    break;
            }
            if((xRange[0] == IMPOSSIBLE )|| (yRange[0] == IMPOSSIBLE)){
                return 0;
            }
        }
        
        return (xRange[1] - xRange[0] + 1) * (yRange[1] - yRange[0] + 1);
    }
    
    void move(long[] range, long delta, int axis){
        long limit = axis == ROW ? n : m;
        long lo = range[0];
        long hi = range[1];
        
        long[] movedLo = movePos(lo, delta, limit);
        long[] movedHi = movePos(hi, delta, limit);
        
        if(movedLo == null && movedHi == null){
            range[0] = range[1] = IMPOSSIBLE;
        }else if(movedLo == null){
            range[0] = 0;
            range[1] = movedHi[1];
        }else if(movedHi == null){
            range[0] = movedLo[0];
            range[1] = limit - 1;
        }else{
            range[0] = movedLo[0];
            range[1] = movedHi[1];
        }
    }
    
    long[] movePos(long pos, long delta, long limit){
        if(delta >0){
            long lo = Math.max(0, pos-delta);
            if(pos == limit-1){
                long hi = limit-1;
                return new long[]{lo, hi};
            }else if(pos - delta < lo){
                return null;
            }
            return new long[]{lo, pos - delta};
        }else{
            delta = Math.abs(delta);
            long hi = Math.min(limit-1, pos + delta);
            if(pos == 0){
                long lo = 0;
                return new long[]{lo, hi};
            }else if(pos + delta > hi){
                return null;
            }
            return new long[]{pos + delta, hi};
        }
    }
}