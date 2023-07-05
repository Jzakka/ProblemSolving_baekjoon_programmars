import java.util.*;

class Solution {
    class Wedge{
        int x,y;
        Wedge(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    
    Map<Integer, Integer> xComps = new HashMap();
    Map<Integer, Integer> yComps = new HashMap();
    
    Wedge[] allWedges;
    int[][] partialSum = new int[5001][5001];
    public int solution(int n, int[][] data) {
        allWedges = new Wedge[n];
        int[] allX = Arrays.stream(data).mapToInt(d->d[0]).distinct().sorted().toArray();
        int[] allY = Arrays.stream(data).mapToInt(d->d[1]).distinct().sorted().toArray();
        for(int i = 0;i<allX.length;i++){
            xComps.put(allX[i], i);
        }
        for(int j = 0;j<allY.length;j++){
            yComps.put(allY[j], j);
        }
        
        allWedges = Arrays.stream(data)
            .map(d->new Wedge(xComps.get(d[0]), yComps.get(d[1])))
            .toArray(Wedge[]::new);
        
        for(Wedge w : allWedges){
            partialSum[w.x][w.y] = 1;
        }
        
        int sum = 0;
        for(int i=0;i<5001;i++){
            sum += partialSum[i][0];
            partialSum[i][0] = sum;
        }
        sum = 0;
        for(int i=0;i<5001;i++){
            sum += partialSum[0][i];
            partialSum[0][i] = sum;
        }
        for(int i = 1;i<5001;i++){
            for(int j = 1;j<5001;j++){
                partialSum[i][j] += partialSum[i][j-1] + partialSum[i-1][j] - partialSum[i-1][j-1];
            }
        }
        
        for(int i=0;i<5;i++){
            for(int j = 0;j<5;j++){
                System.out.printf("%2d ", partialSum[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        
        int res = 0;
        for(int i=0;i<n;i++){
            Wedge w1 = allWedges[i];
            for(int j = i+1;j<n;j++){
                Wedge w2 = allWedges[j];
                
                if(w1.x != w2.x && w1.y != w2.y && pitchable(w1,w2)){
                    // System.out.println("PITCH!!!");
                    res++;
                }
            }
        }
        
        return res;
    }
    
    boolean pitchable(Wedge w1, Wedge w2){
        int lX = Math.min(w1.x, w2.x);
        int hX = Math.max(w1.x, w2.x);
        int lY = Math.min(w1.y, w2.y);
        int hY = Math.max(w1.y, w2.y);
        
        int wedgeCnt = partialSum[hX-1][hY-1] - partialSum[lX][hY-1] - partialSum[hX-1][lY] + partialSum[lX][lY];
        
        // System.out.printf("(%d,%d) ~ (%d,%d)에 %d개의 쐐기존재%n", lX,lY,hX,hY,wedgeCnt);
        
        return wedgeCnt == 0;
    }
    
}

/**

두 점이 만드는 텐트범위에 다른 쐐기가 들어있는지 아닌지를 판단해야함


*/