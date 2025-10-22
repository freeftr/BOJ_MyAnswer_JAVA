import java.util.*;
class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        long one = 0;
        long two = 0;
        
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();
        
        for (int i = 0; i < queue1.length; i++) {
            one += queue1[i];
            two += queue2[i];
            q1.add(queue1[i]);
            q2.add(queue2[i]);
        }
        
        if (q1.isEmpty() && q2.isEmpty()) {
            return 0;
        }
        
        long limit = q1.size() + q2.size();
        
        while (answer <= limit + 1) {
            if (one > two) {
                int temp = q1.poll();
                q2.add(temp);
                one -= temp;
                two += temp;
            } else if (one == two) {
                break;
            } else if (two > one) {
                int temp = q2.poll();
                q1.add(temp);
                one += temp;
                two -= temp;
            }
            answer++;
        }
        
        return one == two ? answer : -1;
    }
}

/*
조건
- 하나의 큐에서 다른 큐에 넣는 작업으로 두 큐의 합이 같게.
- pop + insert = 1;

요구
- 필요한 최소 횟수.
*/