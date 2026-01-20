import java.io.*;
import java.util.*;

public class Main {
    static int A, B, N, M;
    static final int MAX = 100000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] dist = new int[MAX + 1];
        Arrays.fill(dist, -1);

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(N);
        dist[N] = 0;

        while (!q.isEmpty()) {
            int x = q.poll();
            if (x == M) {
                System.out.println(dist[x]);
                return;
            }

            int[] nexts = {
                    x - 1, x + 1,
                    x - A, x + A,
                    x - B, x + B,
                    x * A, x * B
            };

            for (int nx : nexts) {
                if (nx < 0 || nx > MAX) continue;
                if (dist[nx] != -1) continue;
                dist[nx] = dist[x] + 1;
                q.add(nx);
            }
        }
    }
}


/*
조건
- 돌다리 위에 동규 주미 있음.
- 동규 N 주미 M
- A배 B배 또는 +1, -1 가능

 */