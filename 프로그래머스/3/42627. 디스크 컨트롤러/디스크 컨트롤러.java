import java.io.*;
import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs,(o1,o2) -> o1[0]-o2[0]);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> o1[1]-o2[1]);
        
        int time = 0;
        int idx = 0;
        int answer = 0;
        
        while(true){
            
            if(pq.isEmpty() && idx >= jobs.length){
                break;
            }
            
            while(true){
                if(idx < jobs.length && jobs[idx][0] <= time){
                    pq.offer(jobs[idx]);
                    idx++;
                }
                else{
                    break;
                }
            }
            
            if(pq.isEmpty()){
                time = jobs[idx][0];
            }
            else{
                int[] job = pq.poll();
                time += job[1];
                answer += time-job[0];
            }
        }
        
        return answer/jobs.length;
    }
}