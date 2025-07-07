import java.io.*;
import java.util.*;

public class Main {

    static int n, d, c;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
    static int[] dist;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {

            graph = new ArrayList<>();

            st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            for (int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            dist = new int[n+1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[c] = 0;

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                graph.get(b).add(new int[]{a, s});
            }

            dijkstra(c);

            int answer = 0;
            int max = 0;
            for (int i = 1; i <= n; i++) {
                if (dist[i]!=Integer.MAX_VALUE) {
                    answer++;
                    max = Math.max(max, dist[i]);
                }
            }
            sb.append(answer + " " + max + "\n");
        }

        System.out.println(sb);
    }

    private static void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            if (dist[cur[0]] < cur[1]) continue;

            for (int[] next : graph.get(cur[0])) {
                if (dist[next[0]] > dist[cur[0]] + next[1]) {
                    dist[next[0]] = dist[cur[0]] + next[1];
                    pq.offer(new int[]{next[0], dist[next[0]]});
                }
            }
        }
    }
}
