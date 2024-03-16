import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

public class Solution {
    char[] directions = {'d','l','r','u'};
    int[] dx = {1,0,0,-1};
    int[] dy = {0,-1,1,0};

    int n,m;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        this.n = n;
        this.m = m;
        x--;y--;r--;c--;

        int dist = Math.abs(x-r) + Math.abs(y-c);
        if((dist&1) != (k&1) || dist > k){
            return "impossible";
        }
        
        StringBuilder ans=new StringBuilder();
        while(k-->0){
            for(int i=0;i<4;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
            
                if(inRange(nx,ny) && (dist = Math.abs(nx-r) + Math.abs(ny-c)) <= k){
                    ans.append(directions[i]);
                    x=nx;
                    y=ny;
                    break;
                }
            }   
        }
        return ans.toString();
    }

    boolean inRange(int x,int y){
        return 0<=x && x < n && 0<= y && y<m;
    }
}