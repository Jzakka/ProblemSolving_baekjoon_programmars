import java.util.*;

import static java.lang.System.*;

class Solution {
    private final int MAX_DEGREE = 12 * 3600;
    private final int NOON = getTimestamp(12, 0, 0);
    
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int ans = getCount(h2, m2, s2) - getCount(h1,m1,s1);
        if((h1==0&&m1==0&&s1==0 ) || (h1==12&&m1==0&&s1==0 )){
            ans++;
        }
        return ans;
    }
    
    private int getCount(int h, int m, int s){
        int ans = (h*60 + m) * 2;
        
        int ts = getTimestamp(h,m,s);
        double hw = ts % MAX_DEGREE;
        double mw = ts*12 % MAX_DEGREE;
        double sw = ts*720 % MAX_DEGREE;
        
        if(hw <= sw){
            ans++;
        }
        if(mw <= sw){
            ans++;
        }
        // 00:00:00 => 하나로 계산해야 함
        ans--;
    
        // xx:00:00 => 분침과 초침은 만나지 않음
        ans -= h;
        
        if(ts >= NOON){ // 12:00:00
            ans -= 2;
        }
        
        return ans;
    }
    
    private int getTimestamp(int h, int m, int s){
        return 3600 * h + 60 * m + s;
    }
}