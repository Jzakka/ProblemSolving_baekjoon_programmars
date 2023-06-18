import java.util.*;

class Solution {
    Map<Integer, Integer> map = new HashMap();
    TreeMap<Integer, List<Integer>> nodes = new TreeMap((a,b)->(int)b-(int)a);
    
    List<Integer> pre = new ArrayList();
    List<Integer> post = new ArrayList();
    Map<Integer, Integer> hashedNode = new HashMap();
    int[] keys;
    
    public int[][] solution(int[][] nodeinfo) {
        int i=0;
        for(int[] node : nodeinfo){
            int x = node[0];
            int y = node[1];
            hashedNode.put(cantorPair(x,y), ++i);
            
            if(!nodes.containsKey(y)){
                List<Integer> list = new ArrayList();
                list.add(x);
                nodes.put(y, list);
            }else{
                nodes.get(y).add(x);
            }
            
            for(List<Integer> xValues:nodes.values()){
                Collections.sort(xValues);
            }   
        }
        
        keys = nodes.keySet().stream().mapToInt(Integer::intValue).toArray();
        
        // for(int key:nodes.keySet()){
        //     System.out.println("KEY: " + key);
        //     System.out.print("\tVALUE:");
        //     for(int value: nodes.get(key)){
        //         System.out.print(value + ",");
        //     }
        //     System.out.println();
        // }
        
        preOrder(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        postOrder(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        
        return new int[][]{pre.stream().mapToInt(Integer::intValue).toArray(),
                        post.stream().mapToInt(Integer::intValue).toArray()};
    }
    
    void preOrder(int keyIdx, int left, int right){
        if(keyIdx == keys.length){
            return;
        }
        
        int childY = keys[keyIdx];
        List<Integer> childrenX = nodes.get(childY);
        int childX = inRange(left, right, childrenX);
        
        if(childX == -1){
            return;
        }
        
        pre.add(hashedNode.get(cantorPair(childX, childY)));
        preOrder(keyIdx+1, left, childX);
        preOrder(keyIdx+1, childX, right);
    }
    
    void postOrder(int keyIdx, int left, int right){
        if(keyIdx == keys.length){
            return;
        }
        
        int childY = keys[keyIdx];
        List<Integer> childrenX = nodes.get(childY);
        int childX = inRange(left, right, childrenX);
        
        if(childX == -1){
            return;
        }
        
        postOrder(keyIdx+1, left, childX);
        postOrder(keyIdx+1, childX, right);
        post.add(hashedNode.get(cantorPair(childX, childY)));
    }
    
    int inRange(int left, int right, List<Integer> childrenX){
        for(int childX : childrenX){
            if(left < childX && childX < right){
                return childX;
            }
        }
        return -1;
    }
    
    
    int cantorPair(int a, int b){
        return (a+b)*(a+b+1)/2 + b;
    }
}