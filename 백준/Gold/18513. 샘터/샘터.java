import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        HashSet<Integer> visited = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());

            pq.add(new int[]{a, 0});
            visited.add(a);
        }

        long answer = 0;
        K += N;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int v = cur[0];
            int d = cur[1];

            K--;
            answer += cur[1];

            if (K == 0) break;

            if (!visited.contains(v + 1)) {
                pq.add(new int[]{v + 1, d + 1});
                visited.add(v + 1);
            }

            if (!visited.contains(v - 1)) {
                pq.add(new int[]{v - 1, d + 1});
                visited.add(v - 1);
            }
        }

        System.out.println(answer);
    }
}

/*
조건
- N개의 샘터, K채의 집
- 불행도 : 가장 가까운 샘터까지의 거리
- 가능한 샘터 주변에 집 지음


요구
- 모든 집에 대한 불행도 합의 최솟값

풀이
- 샘터 위치 큐에 다 넣고 bfs
 */
