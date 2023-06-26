import java.util.*;

class Solution {
    public int solution(String[] lines) {
        List<Servlet> servlets = new ArrayList();
        for(String line:lines){
            servlets.add(new Servlet(line.substring(11)));
        }
        
        PriorityQueue<Servlet> heap = new PriorityQueue<Servlet>((s1,s2)->{
            return (int)(s2.reqTime - s1.reqTime);
        });
        
        int maxSize = -1;
        for(int i = servlets.size() - 1;i >=0;i--){
            Servlet servlet = servlets.get(i);
            // System.out.println("LATEST:" + servlet.original);
            
            if(heap.isEmpty()){
                heap.add(servlet);
            }else{
                
                while(!heap.isEmpty() && heap.peek().reqTime >= servlet.resTime + 1000){
                    Servlet polled = heap.poll();
                    
                    // System.out.println("\tPOLLED:" + polled.original);
                }
                heap.add(servlet);
            }
            maxSize = Math.max(heap.size(), maxSize);
            // System.out.println("SIZE:" + heap.size());
        }
        
        
        return maxSize;
    }
    
    static class Servlet{
        long reqTime;
        long resTime;
        String original;
        
        Servlet(String time){
            original = time;
            String[] info = time.split(" ");
            
            String endTime = info[0];
            String process = info[1];
            long processingTime = (long)Math.floor(Double.parseDouble(process.substring(0, process.length() - 1)) * 1000);
            
            String[] times = endTime.split(":");
            long hours = Long.parseLong(times[0]) * 3600 * 1000;
            long minutes = Long.parseLong(times[1]) * 60 * 1000;
            long milliSeconds = (long)Math.floor(Double.parseDouble(times[2]) * 1000);
            
            resTime = hours + minutes + milliSeconds;
            reqTime = resTime - processingTime + 1;
            
            // System.out.println(time+"의 timeStamp 는 " + reqTime + " ~ " + resTime);
        }
    }
}

/*
e - s + 0.001 = t
s = e - t + 0.001

4.001 - 2.0 + 0.001 = 2.002
7 - 2 + 0.001 = 5.001

4.002 - 2.0 + 0.001 = 2.003
2.003 ~ 4.002
5.001 ~ 7.000

7.000 - 2 + 0.001 = 5.001

24 * 60 * 60 * 1000


*/