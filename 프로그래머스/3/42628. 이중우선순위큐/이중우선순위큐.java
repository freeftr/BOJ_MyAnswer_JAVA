import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((a, b) -> b - a);
        PriorityQueue<Integer> minQ = new PriorityQueue<>((a, b) -> a - b);
        
        for (String operation : operations) {
            String[] s = operation.split(" ");
            String cmd = s[0];
            int num = Integer.parseInt(s[1]);
            
            if (cmd.equals("I")) {
                maxQ.add(num);
                minQ.add(num);
            } else {
                if (maxQ.isEmpty()){
                    continue;
                }
                if (num == 1) {
                    int a = maxQ.poll();
                    minQ.remove(a);
                } else {
                    int a = minQ.poll();
                    maxQ.remove(a);
                }
            }
        }
        
        if (maxQ.isEmpty()) {
            answer = new int[]{0,0};
        } else {
            answer = new int[]{maxQ.poll(), minQ.poll()};
        }
        
        return answer;
    }
}
