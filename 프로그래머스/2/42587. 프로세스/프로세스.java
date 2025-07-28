import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        Queue<int[]> q = new ArrayDeque<>();
        
        for (int i = 0; i < priorities.length; i++) {
            pq.add(priorities[i]);
            q.add(new int[]{priorities[i], i});
        }
        
        while (!q.isEmpty()) { 
            int[] cur = q.poll();
            
            if (pq.peek() > cur[0]) {
                q.add(cur);
            } else {
                answer++;
                pq.poll();
                if (cur[1] == location) {
                    break;
                }
            }
        }
        return answer;
    }
}