import java.io.*;
import java.util.*;

class Solution {
    
    public long solution(int[] sequence) {
        long answer1 = Integer.MIN_VALUE;
        long answer2 = Integer.MIN_VALUE;
        long sum1 = 0;
        long sum2 = 0;
        long min1 = 0;
        long min2 = 0;
        
        for(int i = 0; i < sequence.length; i++){
            if(i%2==0){
                sum1+=sequence[i];  
            }
            else{
                sum1+=-sequence[i];
            }
            answer1 = Math.max(sum1-min1,answer1);
            min1 = Math.min(sum1, min1);
        }
        
        for(int i = 0; i < sequence.length; i++){
            if(i%2==0){
                sum2+=-sequence[i];
            }
            else{
                sum2+=sequence[i];
            }
            answer2 = Math.max(sum2-min2,answer2);
            min2 = Math.min(sum2, min2);
        }
        return Math.max(answer1, answer2);
    }
}