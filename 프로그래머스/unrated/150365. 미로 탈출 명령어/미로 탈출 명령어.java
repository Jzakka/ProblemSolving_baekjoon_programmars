class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int distance = Math.abs(x-r) + Math.abs(y-c);
        if((distance & 1) != (k & 1) || distance > k){
            return "impossible";
        }
        
        int[] dx = {1, 0,0,-1}; // D L R U
        int[] dy = {0, -1, 1, 0}; // D L R U
        char[] dirs = {'d','l','r','u'}; // D L R U
        
        StringBuilder sb = new StringBuilder();
        
        // System.out.printf("X:%d, Y:%d%n", x,y);
        
        while(k-- > 0){
            for(int i=0;i<4;i++){
                int movedX = x + dx[i];
                int movedY = y + dy[i];
                
                if(Math.abs(movedX - r) + Math.abs(movedY - c) <= k 
                   && movedX > 0 && movedX <= n && movedY > 0 && movedY <= m){
                    x = movedX;
                    y = movedY;
                    sb.append(dirs[i]);
                    break;
                }
            }
            // System.out.printf("X:%d, Y:%d%n", x,y);
        }
        
        return sb.toString();
    }
}

/*
dlru



*/