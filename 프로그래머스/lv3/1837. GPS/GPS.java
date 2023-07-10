import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        List<Integer>[] incidents;
        
        incidents = new List[n+1];
        for(int i=1;i<=n;i++){
            incidents[i] = new ArrayList(Arrays.asList(i));
        }

        for(int[] edge:edge_list){
            int src = edge[0];
            int dest = edge[1];

            incidents[src].add(dest);
            incidents[dest].add(src);
        }
        
        int[][] DP = new int[k][n+1];
        
        Arrays.fill(DP[0], Integer.MAX_VALUE);
        DP[0][gps_log[0]] = 0;
        
        for(int i=1;i<k-1;i++){
            int prevLogIdx = i-1;
            for(int j=1;j<=n;j++){
                DP[i][j] = incidents[j].stream()
                    .mapToInt(incident -> DP[prevLogIdx][incident])
                    .min().getAsInt();
                if(DP[i][j] != Integer.MAX_VALUE && j != gps_log[i]){
                    DP[i][j]++;
                }
            }
        }
        
        // for(int i=0;i<k;i++){
        //     for(int j=1;j<=n;j++){
        //         System.out.printf("%2s |", DP[i][j] < 150_000 ? DP[i][j] : "I");
        //     }
        //     System.out.println();
        // }
    
        int prevK = k-2;
        int res= incidents[gps_log[k-1]].stream()
            .mapToInt(incident -> DP[prevK][incident])
            .min().getAsInt();
        
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}