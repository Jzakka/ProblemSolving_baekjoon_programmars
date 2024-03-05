class Solution {
    long[][] pascal = new long[301][301];
    final long MOD = 10000019;
    
    public void setPascal(){
        for(int i=0;i<=300;i++){
            pascal[i][0] = 1;
            pascal[i][i] = 1;
        }
        for(int i=2;i<=300;i++){
            for(int j=1;j<i;j++){
                pascal[i][j] = (pascal[i-1][j-1] + pascal[i-1][j])%MOD;
            }
        }
    }
    
    public int solution(int[][] a) {
        setPascal();
        int n = a.length;
        int m = a[0].length;
        
        int[] ones = getOnes(n,m,a);
        
        long[][] DP = new long[m][n + 1];
        
        DP[0][n-ones[0]]  = comb(n, ones[0]);
        
        calculate(n,m,ones,DP);
        
        // debug(ones, DP);
        
        return (int)DP[m-1][n];
    }
    
    int[] getOnes(int n, int m, int[][] mat){
        int[] ones = new int[m];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(mat[j][i] == 1){
                    ones[i]++;
                }
            }
        }
        
        return ones;
    }
    
    long comb(int a, int b){
        return pascal[a][b];
    }
    
    void calculate(int n, int m, int[] ones, long[][] DP){
        for(int i = 0;i<DP.length - 1;i++){
            for(int j = 0;j<DP[0].length;j++){
                if(DP[i][j] > 0){
                    int l = ones[i + 1];
                    invest(DP, ones, i,j,l);
                }
            }
        }
    }
    
    void invest(long[][] DP, int[] ones, int i, int j, int oneCnt){
        int evenCnt = j;
        int oddCnt = DP[0].length -1 - evenCnt;
        
        // System.out.printf("i:%d, j:%d, evenCnt:%d, oddCnt:%d%n",i,j,evenCnt,oddCnt);
        
        for(int t = 0;t <= Math.min(evenCnt, oneCnt);t++){
            int investToOdd = oneCnt - t;
            
            // System.out.printf("\tinvestToOdd:%d %n",investToOdd);
            
            if(investToOdd > oddCnt){ continue; }
            
            long selectEven = comb(evenCnt,t);
            long selectOdd = comb(oddCnt, investToOdd);
            
            // System.out.printf("\tDP[%d][%d] = DP[%d][%d] *%d *%d %n", i+1,evenCnt - t + investToOdd, i , j,selectEven, selectOdd);
            DP[i+1][evenCnt - t + investToOdd] += (DP[i][j] * selectEven % MOD) * selectOdd % MOD;    
            DP[i+1][evenCnt - t + investToOdd] %= MOD;
        }
    }
    
    void debug(int[] ones, int[][] DP){
        System.out.println("====ones====");
        for(int i=0;i<ones.length;i++){
            System.out.print(ones[i] + " ");
        }
        System.out.println();
        System.out.println("====DP====");
        for(int i=0;i<DP.length;i++){
            for(int j = 0;j<DP[0].length;j++){
                System.out.print(DP[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}