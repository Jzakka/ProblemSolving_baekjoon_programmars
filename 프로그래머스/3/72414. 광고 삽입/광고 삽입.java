import java.util.*;
import java.util.stream.*;

import java.lang.System.*;

class Solution {
    int total;
    int ad;
    int[][] logs;
    int[] table;
    public String solution(String play_time, String adv_time, String[] log_times) {
        total = parse(play_time);
        ad = parse(adv_time);
        table = new int[total + 1];
        
        Arrays.stream(log_times)
            .map(l -> {
                String[] times = l.split("-");
                return new int[]{parse(times[0]), parse(times[1])};
            })
            .forEach(log -> {
                table[log[0]]++;
                if(log[1] + 1<= total){
                    table[log[1]]--;
                }
            });
        
        int acc = 0;
        for(int i=0;i<=total;i++){
            acc += table[i];
            table[i] = acc;
        }
        
        long winSum = IntStream.range(0, ad)
            .mapToLong(i -> table[i])
            .sum();
        int ans = 0;
        long maxTime = winSum;
        
        for(int i=ad;i<=total;i++){
            winSum -= table[i-ad];
            winSum += table[i];
            if(maxTime < winSum){
                maxTime = winSum;
                ans = i - ad + 1;
            }
        }
        
        return stringfy(ans);
    }
    
    int parse(String time){
        String[] info = time.split(":");
        int hour = Integer.parseInt(info[0]);
        int minute = Integer.parseInt(info[1]);
        int second = Integer.parseInt(info[2]);
        
        return hour * 3600 + minute * 60 + second;
    }
    
    String stringfy(int timestamp){
        int hour = timestamp / 3600;
        timestamp -= hour * 3600;
        int minute = timestamp/60;
        timestamp -= minute * 60;
        return String.format("%02d:%02d:%02d", hour, minute, timestamp);
    }
}