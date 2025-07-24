import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int k : scoville) {
            pq.add(k);
        }

        while (pq.size() > 1) {
            if (pq.peek() >= K) break;

            int first = pq.poll();
            int second = pq.poll();
            int mixed = first + (second * 2);

            pq.add(mixed);
            answer++;
        }

        if (pq.peek() < K) {
            return -1;
        }

        return answer;
    }
}
