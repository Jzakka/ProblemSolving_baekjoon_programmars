import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[][] scores) {
        int[] wanho = scores[0];
        Arrays.sort(scores, Comparator.comparingInt((int[] score) -> -score[0]).thenComparingInt(score-> score[1]));
        
        int max = 0;
        int rank = 1;
        for(int[] score:scores){
            if(score[1] < max){
                if(score == wanho){
                    return -1;
                }
            }else{
                max = score[1];
                if(score[0] + score[1] > wanho[0] + wanho[1]){
                    rank++;
                }
            }
        }
        
        return rank;
    }
}