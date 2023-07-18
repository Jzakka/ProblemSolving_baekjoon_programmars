import java.util.*;

class Solution {
    Map<Long, Long> map = new HashMap();
    
    public long[] solution(long k, long[] room_number) {
        long[] result = new long[room_number.length];
        
        int i=0;
        for(long roomNum:room_number){
            result[i++]=checkin(roomNum);
        }
        
        return result;
    }
    
    long checkin(long roomNum){
        if(!map.containsKey(roomNum)){
            map.put(roomNum, roomNum+1);
            return roomNum;
        }
        
        long roomKey = checkin(map.get(roomNum));
        map.put(roomNum, roomKey);
        return roomKey;
    }
}