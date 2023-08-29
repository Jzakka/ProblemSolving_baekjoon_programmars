import java.util.*;

// 존나 어렵노 시발

class Solution {
    int res = 0;
    
    public int solution(int n) {
        int starCnt = (int)(Math.log(n) / Math.log(3));
        dfs(n-2, starCnt,0,2);
        return res;
    }
    
    void dfs(int currentNum, final int totalStar, int usedStar, int usedCross){
        if(currentNum == 1 && usedStar == totalStar && usedCross == 2*totalStar){
            res++;
            return;
        }
        
        if(2*(usedStar+1) <= usedCross && currentNum % 3 == 0 && currentNum > 2){
            dfs(currentNum/3, totalStar, usedStar+1, usedCross);
        }
        if(usedCross + 1 <= totalStar * 2){
            dfs(currentNum -1, totalStar, usedStar, usedCross + 1);
        }
    }
}