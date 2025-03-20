import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        
        PriorityQueue<Integer> q = new PriorityQueue<>();
        
        for (int i = 0; i < 24; i++){
            int required = players[i]/m;
            int alive = 0;
            
            while(!q.isEmpty() && q.peek() <= i){
                q.poll();
            }
            
            alive = q.size();
            
            if (required <= alive) continue;
            
            answer += required - alive;
            
            for (int j = 0; j < required - alive; j++){
                q.add(i + k);
            }
        }
        return answer;
    }
}