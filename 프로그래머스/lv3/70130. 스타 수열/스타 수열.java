import java.util.*;

class Solution {
    Map<Integer, List<Integer>> indicies = new HashMap();
    int[] sequence;
    int n;
    
    public int solution(int[] a) {
        sequence = a;
        n = a.length;
        for(int i=0;i<a.length;i++){
            int num = a[i];
            
            if(indicies.containsKey(num)){
                indicies.get(num).add(i);
            }else{
                List<Integer> idxList = new ArrayList();
                idxList.add(i);
                indicies.put(num, idxList);
            }
        }
        
        int maxLen = 0;
        for(int key:indicies.keySet()){
            // System.out.printf("KEY:%d%n", key);
            int len = 0;
            int lastSelectedIdx = -1;
            // System.out.print("\tSELECT:");
            for(int idx:indicies.get(key)){
                if(available(key, idx-1, lastSelectedIdx)){
                    // System.out.printf("[%d, %d]",  a[idx-1], key);
                    len++;
                    lastSelectedIdx = idx-1;
                }else if(available(key, idx+1, lastSelectedIdx)){
                    // System.out.printf("[%d, %d] ", key, a[idx+1]);
                    len++;
                    lastSelectedIdx = idx+1;
                }
            }
            // System.out.printf("%nLEN:%d%n", 2*len);
            maxLen = Math.max(maxLen, 2*len);
        }
        
        return maxLen;
    }
    
    boolean available(int key, int idx, int lastSelectedIdx){
        if(idx < 0 || idx >= n || sequence[idx] == key || lastSelectedIdx == idx){
            return false;
        }
        return true;
    }
}