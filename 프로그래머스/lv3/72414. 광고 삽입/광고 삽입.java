class Solution {
    final int SECONDS_OF_DAY = 360_000; // 100:00:00
    int[] times = new int[SECONDS_OF_DAY];
    
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTimeStamp = parseTime(play_time);
        
        for(String log:logs){
            String[] duration = log.split("-");
            int start = parseTime(duration[0]);
            int end = parseTime(duration[1]);
            times[start]++;
            times[end]--;
        }
        
        // 시간의 겹치는 횟수 누적합 계산
        int sum = 0;
        for(int i=0;i<playTimeStamp;i++){
            sum += times[i];
            times[i] = sum;
        }
        
        // 윈도우 생성
        int s = 0;
        int e = parseTime(adv_time);
        
        long windowSum = 0;
        for(int i = 0; i<e;i++){
            windowSum += times[i];
        }
        
        long maxSum = windowSum;
        int startAtMax = 0;
        
        
        // 슬라이딩 윈도우
        for(; e< playTimeStamp;e++,s++){
            windowSum -= times[s];
            windowSum += times[e];
            if(windowSum > maxSum){
                maxSum = windowSum;
                startAtMax = s+1;
            }
        }
        
        return parseTimeStamp(startAtMax);
    }
    
    int parseTime(String time){
        String[] components = time.split(":");
        int hh = Integer.parseInt(components[0]);
        int mm = Integer.parseInt(components[1]);
        int ss = Integer.parseInt(components[2]);
        
        return 3600 * hh + 60 * mm + ss;
    }
    
    String parseTimeStamp(int timeStamp){
        int hh = timeStamp / 3600;
        int mm = (timeStamp - hh*3600)/60;
        int ss = (timeStamp - hh*3600 - mm*60);
        
        return String.format("%02d:%02d:%02d", hh,mm,ss);
    }
}