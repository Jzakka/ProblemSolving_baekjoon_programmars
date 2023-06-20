import java.util.*;

class Solution {
    int n;
    public int solution(int n, int[] weak, int[] dist) {
        this.n = n;
        int answer = Integer.MAX_VALUE;
        
        int cnt = 0;
        do{
            int needs = check(weak, dist);
            answer = Math.min(answer, needs);    
        }while(nextPermutation(dist));
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    int repair(int[] expanded, int[] dist,int start,int end){
        int distIdx=0;
        int i = start;
        for(;i<end&&distIdx<dist.length;distIdx++){
            int w = expanded[i];
            
            int stopped = w + dist[distIdx];
            
            // System.out.printf("\t[%d ~ %d]%n", w, stopped);
            
            while(expanded[i] <= stopped && i<end){
                i++;
            }
        }
        
        // System.out.println("\t[END:"+expanded[end]+"]");
        
        if(i < end){
            // System.out.println("\tCANNOT COVER");
            return Integer.MAX_VALUE;
        }
        // System.out.println("\tRETURN: " + distIdx);
        return distIdx;
    }
    
    int check(int[] weak, int[] dist){
        /*
        weak[1,5,6,10]
        expanded[1,5,6,10,13,17,18,22]
                
        dist[4,3,2,1]
        시작점 1: 1 + 4 = 5 // 13이 엔드포인트
             6: 6 + 3 = 9 // 
            10: 10 + 2 = 12 // 
        시작점 5: 5 + 4 = 9 // 17이 엔드포인트
             10: 10 + 3 = 13 // 
        시작점id + weak.length 이 endpointIdx
        enpointIdx를 만나면 한바퀴를 돈 것
        */
        
        // 배열 확장
        int[] expanded = new int[2*weak.length];
        int i=0;
        for(;i<weak.length;i++){
            expanded[i] = weak[i];
        }
        for(;i<expanded.length;i++){
            expanded[i] = weak[i-weak.length] + n;
        }
        
        int minNeeds = Integer.MAX_VALUE;
        for(int start = 0;start < weak.length;start++){
            // System.out.printf("START_IDX:%d, START:%d, DIST:[", start, weak[start]);
            // for(int d:dist){
            //     System.out.print(d + ",");
            // }
            // System.out.println("]");
            
            int end = start + weak.length;
            
            int needs = repair(expanded, dist, start, end);
            minNeeds = Math.min(needs, minNeeds);
        }
        
        return minNeeds;
    }
    
    boolean nextPermutation(int[] arr){
        for(int i=arr.length-1;i>0;i--){
            if(arr[i-1] < arr[i]){
                int idx = upper(arr, i, arr.length, arr[i-1]);
                if(idx == -1){
                    return false;
                }
                
                int temp = arr[i-1];
                arr[i-1] = arr[idx];
                arr[idx] = temp;
                
                Arrays.sort(arr, i, arr.length);
                return true;
            }
        }
        
        return false;
    }
    
    int upper(int[] arr,int start, int end, int target){
        int min = Integer.MAX_VALUE;
        int minIdx = -1;
        
        for(int i=start;i<end;i++){
            if(target < arr[i] && min > arr[i]){
                min = arr[i];
                minIdx = i;
            }
        }
        
        return minIdx;
    }
}

/*

[1 5 6 10]

1 2 3 4
1 2 4 3
1 3 2 4


*/