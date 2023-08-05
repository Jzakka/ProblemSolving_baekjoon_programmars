class Solution {
    String[] arr;
    int[][] DP = new int[100_000][];
    public int solution(String arr[]) {
        this.arr = arr;
        int opSize = arr.length/2;
        int res = Integer.MIN_VALUE;
        for(int i = 0;i<opSize;i++){
            int opIdx = 2*i + 1;
            String op = arr[opIdx];
            int left = calc(0,opIdx)[0];
            int[] rights = calc(opIdx+1, arr.length);
            // System.out.println("LEFT " + left);
            if(op.equals("+")){
                // System.out.println("RIGHT " + rights[1]);
                res = Math.max(res, left + rights[1]);
            }else{
                // System.out.println("RIGHT " + rights[0]);
                res = Math.max(res, left - rights[0]);
            }
        }
        
        return res;
    }
    
    // {최소결과, 최대결과}
    int[] calc(int start, int end){ // arr의 탐색범위 [start, end)
        // System.out.printf("탐색범위[%d,%d)%n", start,end);
        int key = cantorPair(start, end);
        if(DP[key] != null){
            return DP[key];
        }
        
        //로직
        if(end-start == 1){ // 탈출조건
            // System.out.println("\tESCAPE");
            int num = Integer.valueOf(arr[start]);
            DP[key] = new int[]{num, num};
            return DP[key];
        }
        
        int minRes = Integer.MAX_VALUE;
        int maxRes = Integer.MIN_VALUE;
        for(int opIdx = start+1;opIdx<end;opIdx+=2){
            int eval;
            String op = arr[opIdx];
            int left = calc(start,opIdx)[0];
            int[] rights = calc(opIdx+1, end);
            if(op.equals("+")){
                eval = left + rights[1];
            }else{
                eval =  left - rights[0];
            }
            minRes = Math.min(minRes, eval);
            maxRes = Math.max(maxRes, eval);
        }
        
        DP[key] = new int[]{minRes, maxRes};
        return DP[key];
    }
    
    int cantorPair(int a, int b){
        return (a+b)*(a+b+1)/2+b;
    }
}