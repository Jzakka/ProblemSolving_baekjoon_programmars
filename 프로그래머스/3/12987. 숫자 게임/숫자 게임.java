import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int j = 0;
        int ans = 0;
        for(int a: A){
            while(j < B.length && B[j] <= a){
                j++;
            }
            if(j == B.length){
                break;
            }
            ans++;
            j++;
        }
        return ans;
    }
}