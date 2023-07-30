class Solution {
    public int solution(int[] cookie) {
        int n = cookie.length;
        int[] sum = new int[n+1];
        int s = 0;
        for(int i=0;i<n;i++){
            s += cookie[i];
            sum[i+1] = s;
        }
        
        int res = 0;
        for(int l = 1;l<=n;l++){
            for(int m = l;m<=n;m++){
                int A = sum[m] - sum[l-1];
                int start = m+1;
                int end = n;
                
                while(start <= end){
                    int mid = (start + end)/2;
                    int B = sum[mid] - sum[m];
                    if(A == B){
                        res = Math.max(res, A);
                        break;
                    }else if(A < B){
                        end = mid - 1;
                    }else {
                        start = mid + 1;
                    }
                }
            }
        }
        
        return res;
    }
}