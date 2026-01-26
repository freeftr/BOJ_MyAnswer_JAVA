import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int p = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        parent = new int[p + 1];

        for (int i = 0; i < p + 1; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);

        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.add(new int[]{s, e, cost});
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (find(cur[0]) != find(cur[1])) {
                union(cur[0], cur[1]);
            }

            if (find(c) == find(v)) {
                answer = cur[2];
                break;
            }
        }

        System.out.println(answer);
    }

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[b] = a;
    }
}

/*
조건
- 각 길마다 너비에 비례하는 군사가 지나갈 수 있음.
- 경로 상에 있는 길중 가장 너비가 좁은 길을 고름.

요구
- 어느 길로 보냈는지

풀이
-
 */
