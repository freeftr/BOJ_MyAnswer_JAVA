import java.io.*;
import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        
        for (int work : works){
            pq.add(work);
        }
        
        for (int i = 0; i < n; i++){
            if (pq.isEmpty()){
                break;
            }
            
            int a = pq.poll();
            
            a--;
            
            if (a!=0){
                pq.add(a);
            }
        }
        
        for (int work : pq){
            answer += work*work;
        }
        
        return answer;
    }
}