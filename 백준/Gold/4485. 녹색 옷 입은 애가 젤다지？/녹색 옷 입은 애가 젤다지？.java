import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] graph;
    static int[][] dist;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int idx = 0;
        while (true) {
            idx++;
            N = Integer.parseInt(br.readLine());
            graph = new int[N][N];

            if (N == 0) break;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            System.out.printf("Problem %d: %d\n", idx, dijkstra());
        }
    }

    public static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = graph[0][0]; 
        pq.add(new Node(0, 0, dist[0][0]));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (dist[nx][ny] > dist[now.x][now.y] + graph[nx][ny]) {
                    dist[nx][ny] = dist[now.x][now.y] + graph[nx][ny];
                    pq.add(new Node(nx, ny, dist[nx][ny]));
                }
            }
        }
        return dist[N - 1][N - 1];
    }
}