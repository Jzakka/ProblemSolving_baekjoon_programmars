import java.util.*;
import java.util.stream.*;

class Solution {
    static int[] dist = new int[100];
    static {
        // 1
        dist[11] = 1;
        dist[12] = 2;
        dist[13] = 4;
        dist[14] = 2;
        dist[15] = 3;
        dist[16] = 5;
        dist[17] = 4;
        dist[18] = 5;
        dist[19] = 6;
        dist[10] = 7;
        
        // 2
        dist[21] = 2;
        dist[22] = 1;
        dist[23] = 2;
        dist[24] = 3;
        dist[25] = 2;
        dist[26] = 3;
        dist[27] = 5;
        dist[28] = 4;
        dist[29] = 5;
        dist[20] = 6;
        
        // 3
        dist[31] = 4;
        dist[32] = 2;
        dist[33] = 1;
        dist[34] = 5;
        dist[35] = 3;
        dist[36] = 2;
        dist[37] = 6;
        dist[38] = 5;
        dist[39] = 4;
        dist[30] = 7;
        
        // 4
        dist[41] = 2;
        dist[42] = 3;
        dist[43] = 5;
        dist[44] = 1;
        dist[45] = 2;
        dist[46] = 4;
        dist[47] = 2;
        dist[48] = 3;
        dist[49] = 5;
        dist[40] = 5;
        
        // 5
        dist[51] = 3;
        dist[52] = 2;
        dist[53] = 3;
        dist[54] = 2;
        dist[55] = 1;
        dist[56] = 2;
        dist[57] = 3;
        dist[58] = 2;
        dist[59] = 3;
        dist[50] = 4;
        
        // 6
        dist[61] = 5;
        dist[62] = 3;
        dist[63] = 2;
        dist[64] = 4;
        dist[65] = 2;
        dist[66] = 1;
        dist[67] = 5;
        dist[68] = 3;
        dist[69] = 2;
        dist[60] = 5;
        
        // 7
        dist[71] = 4;
        dist[72] = 5;
        dist[73] = 6;
        dist[74] = 2;
        dist[75] = 3;
        dist[76] = 5;
        dist[77] = 1;
        dist[78] = 2;
        dist[79] = 4;
        dist[70] = 3;
        
        // 8
        dist[81] = 5;
        dist[82] = 4;
        dist[83] = 5;
        dist[84] = 3;
        dist[85] = 2;
        dist[86] = 3;
        dist[87] = 2;
        dist[88] = 1;
        dist[89] = 2;
        dist[80] = 2;
        
        // 9
        dist[91] = 6;
        dist[92] = 5;
        dist[93] = 4;
        dist[94] = 5;
        dist[95] = 3;
        dist[96] = 2;
        dist[97] = 4;
        dist[98] = 2;
        dist[99] = 1;
        dist[90] = 3;
        
        // 0
        dist[01] = 7;
        dist[02] = 6;
        dist[03] = 7;
        dist[04] = 5;
        dist[05] = 4;
        dist[06] = 5;
        dist[07] = 3;
        dist[8] = 2;
        dist[9] = 3;
        dist[00] = 1;
    }
    
    public int solution(String numbers) {
        Map<Integer, Integer>[] minAcc = new Map[100_002];
        for(int i=0;i<minAcc.length;i++){
            minAcc[i] = new HashMap();
        }
        
        Queue<Integer> Q = new LinkedList();
        Q.add(46);
        minAcc[0].put(46, 0);
        
        int keyIdx = 0;
        while(!Q.isEmpty() && keyIdx < numbers.length()){
            int qLen = Q.size();
            
            for(int i=0;i<qLen;i++){
                int fingers = Q.poll();
                int nextKey = (numbers.charAt(keyIdx)-'0');
                int curWeight = minAcc[keyIdx].get(fingers);
                
                List<Integer> nextFingers = getNextFingers(minAcc[keyIdx+1], curWeight, fingers, nextKey);
                
                Q.addAll(nextFingers);
            }
            keyIdx++;
        }
        
        return minAcc[keyIdx].values().stream().mapToInt(Integer::intValue).min().getAsInt();
    }
    
    List<Integer>  getNextFingers(Map<Integer, Integer> acc, int curWeight,  int fingers, int nextKey){
        List<Integer> nextFingers = new ArrayList();
        int leftThumb = fingers/10;
        int rightThumb = fingers - leftThumb*10;
        
        int leftRes = nextKey*10 + rightThumb;
        int rightRes = leftThumb*10 + nextKey;
        
        // 왼손거리 계산
        int leftDist = getDist(leftThumb, nextKey);
        
        if(leftRes % 11 != 0 && (!acc.containsKey(leftRes) || acc.get(leftRes) > leftDist + curWeight)){
            acc.put(leftRes, leftDist + curWeight);
            nextFingers.add(leftRes);
        }
        //오른손 거리 계산
        int rightDist = getDist(rightThumb, nextKey);
        
        if(rightRes % 11 != 0 && (!acc.containsKey(rightRes) || acc.get(rightRes) > rightDist + curWeight)){
            acc.put(rightRes, rightDist + curWeight);
            nextFingers.add(rightRes);
        }
        
        return nextFingers;
    }
    
    int getDist(int src, int dest){
        return dist[src*10 + dest];
    }
}