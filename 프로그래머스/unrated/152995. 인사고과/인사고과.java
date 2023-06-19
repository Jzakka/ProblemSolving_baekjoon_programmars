import java.util.*;
import java.util.stream.*;

class Solution {
    TreeMap<Integer, TreeSet<Integer>> map = new TreeMap();
    
    public int solution(int[][] scores) {
        //init
        for(int[] score : scores){
            int attitude = score[0];
            int eval = score[1];
            
            if(!map.containsKey(attitude)){
                TreeSet<Integer> evals = new TreeSet();
                evals.add(eval);
                map.put(attitude, evals);
            }else{
                map.get(attitude).add(eval);
            }
        }
        
        // 각 근무점수별 최대 평가점수를 구한 배열
        int[] maxEvals = getMaxEvals();
        
        int[] accMaxes = getAccMaxes(maxEvals);
        
        int i = 0;
        
        Set<List<Integer>> incentives = new LinkedHashSet<>();
        
        for(int att : map.keySet()){
            if(i == map.size()-1){
                List<List<Integer>> lastGroup = map.get(att).stream().map(e->Arrays.asList(att, e)).collect(Collectors.toList());
                incentives.addAll(lastGroup);
            }else{
                for(int eval : map.get(att)){
                    if(accMaxes[i+1] <= eval){
                        incentives.add(Arrays.asList(att, eval));
                    }
                }
            }
            i++;
        }
        
        
        // printAccMaxes(accMaxes);
        // printIncentives(incentives);
        
        List<Integer> wanho = Arrays.asList(scores[0][0], scores[0][1]);
        int wanhoSum = scores[0][0] + scores[0][1];
        // System.out.println(wanho.hashCode());
        
        // 주인공이 incentive를 못받으면 -1리턴
        if(!incentives.contains(wanho)){
            return -1;
        }
        
        // 순위계산
        TreeMap<Integer, Integer> rankOfSum = new TreeMap();
        for(int[] score:scores){
            List<Integer> scoreList = Arrays.asList(score[0], score[1]);
            if(!incentives.contains(scoreList)){
                continue;
            }
                
            int sum = score[0] + score[1];
            if(!rankOfSum.containsKey(sum)){
                rankOfSum.put(sum, 1);
            }else{
                rankOfSum.put(sum, rankOfSum.get(sum) + 1);
            }
        }
        int rank = 1;
        List<Integer> keyList = new ArrayList(rankOfSum.keySet());
        Collections.sort(keyList, (a,b)->b-a);
        
        int keyIdx = 0;
        int revKey = keyList.get(keyIdx);
        while(revKey > wanhoSum){
            rank += rankOfSum.get(revKey);
            revKey = keyList.get(++keyIdx);
        }
        
        return rank;
    }
    
    int[] getMaxEvals(){
        int[] maxEvals = new int[map.size()];
        
        int i=0;
        for(int key : map.keySet()){
            maxEvals[i++] = map.get(key).last();
        }
        
        return maxEvals;
    }
    
    int[] getAccMaxes(int[] maxEvals){
        int curMax = Integer.MIN_VALUE;
        int[] accMaxes = new int[maxEvals.length];
        
        for(int i = maxEvals.length - 1; i >= 0;i--){
            curMax = Math.max (curMax ,maxEvals[i]);
            accMaxes[i] = curMax;
        }
        
        return accMaxes;
    }
    
    
    //=============FOR DEBUG=============
    
    void printIncentives(Collection<List<Integer>> incnts){
        for(List<Integer> incnt : incnts){
            System.out.println("["+incnt.get(0) +","+ incnt.get(1)+"] " + incnt.hashCode());
        }
    }
    
    void printAccMaxes(int[] arr){
        for(int val:arr){
            System.out.print(val + ", ");
        }
        System.out.println();
    }
}