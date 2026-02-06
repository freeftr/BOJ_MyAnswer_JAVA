import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static int[][] union;
    static int[] parent;
    static HashMap<Integer, ArrayList<int[]>> u = new HashMap<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static PriorityQueue<Edge> edges = new PriorityQueue<>((a, b) -> a.c - b.c);

    static class Edge{
        int u;
        int v;
        int c;

        Edge(int u, int v, int c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        union = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = makeUnion();

        parent = new int[max + 1];

        for (int i = 0; i <= max; i++) {
            parent[i] = i;
        }

        makeBridge();

        int cnt = 0;
        int sum = 0;
        while (!edges.isEmpty()) {
            Edge e = edges.poll();

            if (find(e.v) != find(e.u)) {
                union(e.v, e.u);
                cnt++;
                sum += e.c;
            }
        }

        if (cnt != max - 1) System.out.println(-1);
        else System.out.println(sum);

    }

    static void makeBridge() {
        for (int key : u.keySet()) {
            for (int[] p : u.get(key)) {

                for (int d = 0; d < 4; d++) {
                    int nx = p[0];
                    int ny = p[1];
                    int len = 0;
                    int target = -1;

                    while (true) {
                        nx += dx[d];
                        ny += dy[d];
                        len++;

                        if (checkRange(nx, ny)) break;
                        if (union[nx][ny] == key) break;

                        if (union[nx][ny] != 0) {
                            target = union[nx][ny];
                            break;
                        }
                    }

                    len--;

                    if (len >= 2 && target != -1) {
                        edges.add(new Edge(key, target, len));
                    }
                }
            }
        }
    }


    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[a] = b;
    }

    static int makeUnion() {
        int idx = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (union[i][j] != 0) continue;
                if (board[i][j] != 1) continue;

                Queue<int[]> q = new ArrayDeque<>();
                q.add(new int[]{i, j});

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int x = cur[0];
                    int y = cur[1];
                    union[x][y] = idx;
                    u.computeIfAbsent(idx, k -> new ArrayList<>()).add(new int[]{x, y});

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if (checkRange(nx, ny)) continue;
                        if (board[nx][ny] != 1) continue;
                        if (union[nx][ny] == idx) continue;

                        q.add(new int[]{nx, ny});
                    }
                }

                idx++;
            }
        }

        return idx - 1;
    }

    static boolean checkRange(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= M;
    }
}
