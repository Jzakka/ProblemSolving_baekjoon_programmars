import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    int n;
    int[] weak;
    int[] dist;
    int m;
    public int solution(int n, int[] weak, int[] dist) {
        Arrays.sort(dist);
        this.n=n;
        this.weak = weak;
        this.dist = dist;

        extend();

        int ans = Integer.MAX_VALUE;
        do{
            for(int i=0;i<m;i++){
                ans = Math.min(ans, check(i, i + m));
            }
        }while(nextPermutation());

        return ans == Integer.MAX_VALUE ? -1:ans;
    }

    int check(int s, int e){
        int i=s;
        int j=0;
        while(i<e){
            if(j == dist.length){
                return Integer.MAX_VALUE;
            }
            int d = dist[j++];
            int cover = weak[i] + d;
            while(i < e && weak[i] <= cover){
                i++;
            }
        }
        return j;
    }

    boolean nextPermutation(){
        int m = dist.length;

        for(int i = m-2;i>=0;i--){
            if(dist[i] < dist[i+1]){
                int idx = i+1;
                for(int j=i+2;j<m;j++){
                    if(dist[j] > dist[i] && dist[idx] > dist[j]){
                        idx = j;
                    }
                }

                int tmp = dist[i];
                dist[i] = dist[idx];
                dist[idx] = tmp;
                Arrays.sort(dist, i+1, dist.length);
                return true;
            }
        }

        return false;
    }

    void extend(){
        m = weak.length;
        weak = Arrays.copyOf(this.weak, this.weak.length * 2);
        for(int i=m;i< weak.length;i++){
            weak[i] = weak[i-m] + n;
        }
    }

    void debug(){
        for(int e:dist){
            out.print(e + ", ");
        }
        out.println();
    }
}