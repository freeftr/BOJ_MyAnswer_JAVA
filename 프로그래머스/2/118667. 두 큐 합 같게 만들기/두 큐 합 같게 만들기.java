import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        
        long sum1 = 0;
        long sum2 = 0;
        Queue<Integer> a = new LinkedList<>();
        Queue<Integer> b = new LinkedList<>();
        
        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            sum2 += queue2[i];
            
            a.add(queue1[i]);
            b.add(queue2[i]);
        }
        
        if (sum1 == sum2) return 0;
        
        int idx = 0;
        while (sum1 != sum2 && idx <= queue1.length * 2 + 1) {
            if (sum1 > sum2) {
                int at = a.poll();
                sum1 -= at;
                sum2 += at;
                b.add(at);
            } else {
                int bt = b.poll();
                sum2 -= bt;
                sum1 += bt;
                a.add(bt);
            }
            idx++;
            answer++;
        }
        
        if (sum1 != sum2) return -1;
        
        return answer;
    }
}

/*
각 큐의 원소의 합이 같도록
최소 작업 수 구하기

- 각 큐 합 구하면서 큐에 넣기.
- 큰 쪽에서 작은 쪽으로 옮기기
*/