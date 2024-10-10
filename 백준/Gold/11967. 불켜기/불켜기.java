import java.io.*;
import java.util.*;
public class Main {
    static int N, M;
    static boolean[][] visited;
    static boolean[][] light;
    static int ans = 1;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Edge {
        int x, y;
        int a, b;

        public Edge(int x, int y, int a, int b) {
            this.x = x;
            this.y = y;
            this.a = a;
            this.b = b;
        }
    }

    static ArrayList<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        light = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges.add(new Edge(x, y, a, b));
        }

        bfs();

        System.out.println(ans);
    }

    static void bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{1, 1});
        visited[1][1] = true;
        light[1][1] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
//            System.out.println(x + " " + y);
            for(Edge edge : edges) {
                if(x==edge.x && y==edge.y && !light[edge.a][edge.b]) {
                    light[edge.a][edge.b] = true;
                    ans++;

                    for (int i = 0; i < 4; i++) {
                        int nx = edge.a + dx[i];
                        int ny = edge.b + dy[i];
                        if(nx<=0 || ny<=0 || nx>N || ny>N)continue;
                        if(visited[nx][ny]){
                            q.add(new int[]{edge.a, edge.b});
                            visited[edge.a][edge.b] = true;
                            break;
                        }

                    }
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx<=0 || ny<=0 || nx>N || ny>N) continue;
                if(visited[nx][ny])continue;
                if(!light[nx][ny])continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
    }
}