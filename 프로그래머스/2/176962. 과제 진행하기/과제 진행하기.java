import java.util.*;

class Solution {
    static class Plan{
        String name;
        int remain;
        Plan(String name, int remain) {
            this.name = name;
            this.remain = remain;
        }
    }
    
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        Arrays.sort(plans, (a, b) -> a[1].compareTo(b[1]));
        
        Stack<Plan> stack = new Stack<>();
        
        int prev = 0;
        int idx = 0;
        
        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            int start = getTime(plans[i][1]);
            int duration = Integer.parseInt(plans[i][2]);
            int nStart = -1;
            
            if (i != plans.length - 1) {
                nStart = getTime(plans[i + 1][1]);
            }
            
            int time = nStart - start;
            if (nStart >= start + duration) {
                answer[idx++] = name;
                
                time -= duration;
                
                while (!stack.isEmpty() && stack.peek().remain <= time) {
                    Plan p = stack.pop();
                    answer[idx++] = p.name;
                    time -= p.remain;
                }
                
                if (!stack.isEmpty()) {
                    stack.peek().remain -= time;
                }
            } else {
                stack.push(new Plan(name, duration - time));
            }
        }
        
        while (!stack.isEmpty()) {
            answer[idx++] = stack.pop().name;
        }

        
        return answer;
    }
    
    static int getTime(String s) {
        String[] temp = s.split(":");
        int h = Integer.parseInt(temp[0]);
        int m = Integer.parseInt(temp[1]);
        
        return h * 60 + m;
    }
}

/*
조건
- 새로운 과제 시작 시 기존 과제 멈추고 새로운 거 시작

요구
- 과제를 끝낸 순서대로 반환

풀이
- 스택
*/