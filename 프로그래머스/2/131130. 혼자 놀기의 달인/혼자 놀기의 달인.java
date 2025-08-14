import java.util.*;

class Solution {
    static boolean[] visited;
    static int N;

    public int solution(int[] cards) {
        N = cards.length;
        visited = new boolean[N + 1];

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(b, a));

        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;

            int cur = i;
            int cnt = 0;
            while (!visited[cur]) {
                visited[cur] = true;
                cur = cards[cur - 1];
                cnt++;
            }
            if (cnt > 0) pq.add(cnt);
        }

        if (pq.size() < 2) return 0;
        return pq.poll() * pq.poll();
    }
}
