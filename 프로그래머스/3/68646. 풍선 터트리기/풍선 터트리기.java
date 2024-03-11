import java.util.stream.*;

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        int[] fromLeft = new int[n];
        int[] fromRight = new int[n];
        
        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            fromLeft[i] = min;
            min = Math.min(a[i], min);
        }
        
        min = Integer.MAX_VALUE;
        for(int i=n-1;i>=0;i--){
            fromRight[i] = min;
            min = Math.min(a[i], min);
        }
        
        return (int)IntStream.range(0, n)
            .filter(i-> a[i] < fromLeft[i] || a[i] < fromRight[i])
            .count();
    }
}