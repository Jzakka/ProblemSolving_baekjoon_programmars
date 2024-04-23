import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    private final int OFF = Integer.MIN_VALUE;
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        int n = onboard.length;
        
        // DP[i][j] : i번째 onboard에서 실내온도가 j일 때, 최소 소비전력
        int[][] DP = new int[n][51];
        for(int[] row : DP){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        DP[0][getIdx(temperature)] = 0;
        
        for(int i=0;i<n-1;i++){
            // out.printf("OnBoard[#%d], 승객여부:%d%n", i, onboard[i]);
            for(int roomTmp = -10;roomTmp<=40;roomTmp++){
                int curCost = DP[i][getIdx(roomTmp)];
                // out.printf("\t실내온도=%d, 최소소비전력=%d%n", roomTmp, curCost);
                if(curCost == Integer.MAX_VALUE){
                    continue;
                }
                int idx;
                int cost = 0;
                int nextRoomTmp = 0;
                // ON
                for(int hopeTmp = -10;hopeTmp<=40;hopeTmp++){
                    if(hopeTmp < roomTmp){
                        nextRoomTmp = roomTmp-1;
                        cost = a;
                    }else if(hopeTmp == roomTmp){
                        nextRoomTmp = roomTmp;
                        cost = b;
                    }else {
                        nextRoomTmp = roomTmp + 1;
                        cost = a;
                    }
                    
                    if(onboard[i+1] == 0 || (onboard[i+1] == 1 && inRange(t1,t2,nextRoomTmp))){
                        idx = getIdx(nextRoomTmp);
                        DP[i+1][idx] = Math.min(DP[i+1][idx], curCost + cost);
                        // out.printf("\t\t다음 실내온도=%d, 최소소비전력=%d%n", nextRoomTmp, DP[i+1][idx]);
                    }
                }
                // OFF
                cost = 0;
                if(temperature > roomTmp){
                    nextRoomTmp = roomTmp+1;
                }else if(temperature < roomTmp){
                    nextRoomTmp = roomTmp - 1;
                }else{
                    nextRoomTmp = roomTmp;
                }
                
                if(onboard[i+1] == 0 || (onboard[i+1] == 1 && inRange(t1,t2,nextRoomTmp))){
                    idx = getIdx(nextRoomTmp);
                    DP[i+1][idx] = Math.min(DP[i+1][idx], curCost);
                }
            }
        }
        
        return Arrays.stream(DP[n-1]).min().getAsInt();
    }
    
    private boolean inRange(int min, int max, int tmp){
        return min <= tmp && tmp <= max;
    }
    
    private int getTmp(int idx){
        return idx - 10;
    }
    
    private int getIdx(int tmp){
        return tmp + 10;
    }
}