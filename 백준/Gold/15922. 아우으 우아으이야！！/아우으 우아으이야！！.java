import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pq.add(new int[]{x, y});
        }

        int[] start = pq.poll();
        int prev = start[1];
        answer += start[1] - start[0];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int s = cur[0];
            int e = cur[1];

            if (s < prev) {
                if (e < prev) {
                    continue;
                }
                answer += e - prev;
                prev = e;
            } else {
                answer += e - s;
                prev = e;
            }
        }

        System.out.println(answer);
    }
}

/*
조건 + 요구
- 선분을 모두 그렷을 때 그려진 선분 길이 총합 구하기

풀이
-

-5->-2 -3->0 2->5 6->10 8->12
 */
