import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    int n,m;
    int[][] key0;
    int[][] key1;
    int[][] key2;
    int[][] key3;
    
    public boolean solution(int[][] key, int[][] lock) {
        n = key.length;
        m = lock.length;
        
        key0 = key;
        key1 = rotate(key0);
        key2 = rotate(key1);
        key3 = rotate(key2);
        
        int[][][] keys = {key0, key1, key2, key3};
        
        for(int[][] k : keys){
            if(unlock(k, lock)){
                return true;
            }
        }
        return false;
    }
    
    int[][] rotate(int[][] key){
        int[][] rotated = new int[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(key[i][j] == 1){
                    rotated[j][n - i - 1] = 1;
                }
            }
        }
        return rotated;
    }
    
    boolean unlock(int[][] key, int[][] lock){
        for(int i=1;i<=m+n-1;i++){
            for(int j=1;j<=m+n-1;j++){
                int[][] copiedLock = copy(lock);
                
                if(unlock(key, copiedLock, i, j)){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    boolean unlock(int[][] key, int[][] copiedLock, int x, int y){
        for(int i=0;i<n;i++){
            for(int j = 0;j<n;j++){
                int _i = i + x - n;
                int _j = j + y - n;
                
                if(inRange(_i,_j)){
                    copiedLock[_i][_j] += key[i][j];
                }
            }
        }
        
        return Arrays.stream(copiedLock)
            .flatMapToInt(Arrays::stream)
            .allMatch(i -> i == 1);
    }
    
    boolean inRange(int x, int y){
        return 0<=x && x< m&& 0<= y && y<m;
    }
    
    int[][] copy(int[][] lock){
        return IntStream.range(0, m)
            .mapToObj(i -> Arrays.copyOf(lock[i], m))
            .toArray(int[][]::new);
    }
}