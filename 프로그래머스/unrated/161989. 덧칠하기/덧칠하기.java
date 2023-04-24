import java.util.Arrays;

class Solution {
    public int solution(int n, int m, int[] section) {
        int start = section[0];
        int cnt = 1;
        
        for (int i = 1; i < section.length; i++) {
            int ithSection = section[i];
            if (start+m <= ithSection) {
                start = ithSection;
                cnt++;
            }
        }

        return cnt;
    }
}