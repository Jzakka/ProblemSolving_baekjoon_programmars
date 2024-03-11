import java.util.*;
import java.util.stream.*;

import static java.lang.System.*;

class Solution {
    Map<String, Integer> profit = new HashMap();
    Map<String, String> parents = new HashMap();
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        profit.put("center", 0);
        for(int i=0;i<enroll.length;i++){
            String employee = enroll[i];
            String parent = referral[i];
            if(parent.equals("-")){
                parent = "center";
            }
            profit.put(employee, 0);
            parents.put(employee, parent);
        }
        
        for(int i=0;i<seller.length;i++){
            String employee = seller[i];
            int sales =  amount[i]*100;
            
            reap(employee, sales);
        }
        
        return Arrays.stream(enroll)
            .mapToInt(profit::get)
            .toArray();
    }
    
    void reap(String emp, int income){
        if(emp.equals("center") || income == 0){
            return;
        }
        
        int fee = (int)(((double)income) * 0.1);
        // out.printf("emp:%s, income:%d, mine:%d%n", emp, income, mine);
        mapadd(emp, income - fee);
        
        reap(parents.get(emp), fee);
    }
    
    void mapadd(String key, int val){
        profit.put(key, profit.get(key) + val);
    }
}