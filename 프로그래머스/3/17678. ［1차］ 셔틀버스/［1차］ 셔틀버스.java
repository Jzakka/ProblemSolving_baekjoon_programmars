import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    final int FIRST_BUS_START = 9 * 60;
    public String solution(int n, int t, int m, String[] timetable) {
        int[] crews = Arrays.stream(timetable)
            .mapToInt(this::toInt)
            .sorted()
            .toArray();
        int ans=0;
        
        int j = 0;
        for(int i=0;i<n;i++){
            int seats = m;
            int busStart = FIRST_BUS_START + i * t;
            
            while(j<crews.length && crews[j] <= busStart && seats >0){
                j++;
                seats--;
            }
            
            if(i == n-1){
                if(seats > 0){
                    ans = busStart;
                }else{
                    ans = crews[j-1]-1;
                }
            }
        }
        int hh = ans / 60;
        int mm = ans - 60 * hh;
        
        return String.format("%02d:%02d", hh, mm);
    }
    
    int toInt(String time){
        String[] info =time.split(":");
        
        return Integer.parseInt(info[0])*60 + Integer.parseInt(info[1]);
    }
}