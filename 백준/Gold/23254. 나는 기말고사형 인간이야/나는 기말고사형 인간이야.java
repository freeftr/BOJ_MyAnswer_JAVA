import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] A = new int[M];
        int[] B = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) A[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) B[i] = Integer.parseInt(st.nextToken());

        int time = N * 24;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            int g1 = Math.min(o1[1], 100 - o1[0]);
            int g2 = Math.min(o2[1], 100 - o2[0]);
            return g2 - g1;
        });

        long answer = 0;
        for (int i = 0; i < M; i++) {
            A[i] = Math.min(100, A[i]);
            answer += A[i];
            if (A[i] < 100 && B[i] > 0) {
                pq.offer(new int[]{A[i], B[i]});
            }
        }

        while (time > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            int gain = Math.min(cur[1], 100 - cur[0]);
            if (gain <= 0) continue;

            answer += gain;
            cur[0] = Math.min(100, cur[0] + cur[1]);
            time--;

            if (cur[0] < 100) pq.offer(cur);
        }

        System.out.println(answer);
    }
}
