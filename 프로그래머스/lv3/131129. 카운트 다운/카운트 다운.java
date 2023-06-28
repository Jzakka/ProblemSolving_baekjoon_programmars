class Solution {
    int[] singleAndBull = {
            1,2,3,4,5,6,7,8,9,10,
            11,12,13,14,15,16,17,18,19,20,
            50
    };

    int[] doubleAndTriple = {
            21,22,24,26,27,28,30,32,33,34,36,38,39,40,42,45,48,51,54,57,60
    };

    public int[] solution(int target) {
        int[][] DP = new int[target+1][2];
        for(int i=0;i<DP.length;i++){
            DP[i][0] = Integer.MAX_VALUE;
            DP[i][1] = 0;
        }

        DP[0][0] = 0;
        DP[0][1] = 0;

        for(int i=0;i<DP.length;i++){
            for(int j = 0;j<singleAndBull.length;j++){
                int offset = singleAndBull[j];
                if(i+offset <= target){
                    if(DP[i+offset][0] > DP[i][0] + 1 || (DP[i+offset][0] == DP[i][0] + 1 && DP[i+offset][1] < DP[i][1] + 1)){
                        DP[i+offset][0] = DP[i][0] + 1;
                        DP[i+offset][1] = DP[i][1] + 1;
                    }
                }
            }

            for(int j = 0;j<doubleAndTriple.length;j++){
                int offset = doubleAndTriple[j];
                if(i+offset <= target){
                    if(DP[i+offset][0] > DP[i][0] + 1 || (DP[i+offset][0] == DP[i][0] + 1 && DP[i+offset][1] < DP[i][1])){
                        DP[i+offset][0] = DP[i][0] + 1;
                        DP[i+offset][1] = DP[i][1];
                    }
                }
            }
        }

        return DP[target];
    }
}