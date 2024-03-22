import java.util.*;
import java.util.stream.*;

class Solution {
    int[] cnt;
    int[] maxCnts;
    public int[] solution(int e, int[] starts) {
        cnt = new int[e + 1];
        maxCnts = new int[e + 1];
        for(int i=1;i<=e;i++){
            for(int j=1;i*j<=e;j++){
                cnt[i*j]++;
            }
        }
        
        int maxCnt = -1;
        int maxIdx = -1;
        for(int i=e;i>0;i--){
            if(cnt[i] >= maxCnt){
                maxCnt = cnt[i];
                maxIdx = i;
            }
            maxCnts[i] = maxIdx;
        }
        
        return Arrays.stream(starts)
            .map(i -> maxCnts[i])
            .toArray();
    }
}