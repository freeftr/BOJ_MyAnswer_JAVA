import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, fuel;
    static int[][] map;
    static int tx, ty;
    static ArrayList<Client> clients = new ArrayList<>();
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    static boolean[] done;

    static class Client{
        int idx;
        int sx;
        int sy;
        int ex;
        int ey;
        int dist;

        Client(int idx, int sx, int sy, int ex, int ey, int dist) {
            this.idx = idx;
            this.sx = sx;
            this.sy = sy;
            this.ex = ex;
            this.ey = ey;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        done = new boolean[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        tx = Integer.parseInt(st.nextToken()) - 1;
        ty = Integer.parseInt(st.nextToken()) - 1;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int sx = Integer.parseInt(st.nextToken()) - 1;
            int sy = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            int ey = Integer.parseInt(st.nextToken()) - 1;

            clients.add(new Client(i, sx, sy, ex, ey, -1));
        }

        for (int i = 0; i < M; i++) {
            int nextClientIdx = getNextClient();

            if (nextClientIdx == -1) {
                System.out.println(-1);
                return;
            }

            if (!fuelCheck()) {
                System.out.println(-1);
                return;
            }

            Client nextClient = clients.get(nextClientIdx);

            int usedFuel = moveClient(nextClientIdx, nextClient.ex, nextClient.ey);
            fuel -= usedFuel;

            if (usedFuel == -1) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(fuel);
    }

    static int moveClient(int idx, int ex, int ey) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        q.add(new int[]{tx, ty, 0});
        visited[tx][ty] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];

            if (fuel < dist) return -1;

            if (x == ex && y == ey) {
                tx = ex;
                ty = ey;
                done[idx] = true;
                fuel += dist * 2;
                return dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (outRange(nx, ny)) continue;
                if (map[nx][ny] == 1) continue;
                if (visited[nx][ny]) continue;

                q.add(new int[]{nx, ny, dist + 1});
                visited[nx][ny] = true;
            }
        }

        return -1;
    }

    static int getNextClient() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        ArrayList<int[]> candidates = new ArrayList<>(); // idx, sx, sy, dist;

        q.add(new int[]{tx, ty, 0});
        visited[tx][ty] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];

            for (Client c : clients) {
                if (c.sx == x && c.sy == y && !done[c.idx]) {
                    candidates.add(new int[]{c.idx, x, y, dist});
                    break;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (outRange(nx, ny)) continue;
                if (map[nx][ny] == 1) continue;
                if (visited[nx][ny]) continue;

                q.add(new int[]{nx, ny, dist + 1});
                visited[nx][ny] = true;
            }
        }

        Collections.sort(candidates, (a, b) -> {
            if (a[3] == b[3]) {
                if (a[1] == b[1]) {
                    return a[2] - b[2];
                }
                return a[1] - b[1];
            }
            return a[3] - b[3];
        });

        if (candidates.isEmpty()) return -1;

        int[] next = candidates.get(0);

        if (fuel < next[3]) {
            return -1;
        }

        fuel -= next[3];
        tx = next[1];
        ty = next[2];
        return next[0];
    }

    static boolean fuelCheck() {
        return fuel > 0;
    }

    static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }
}

/*
조건
- 손님을 도착지 내려주면 연료 채움.
- 연료 바닥 시 업무 종료.
- M 명 태우는게 목표임.
- 특정위치로 항상 최단 거리로 이동.
- 승객은 빈 칸 -> 빈 칸 이동.
- 이 행동을 M번 반복.
- 가장 가까운 승객부터. 행 열 순.
- 한칸당 연료 1 소모.
- 성공 시 소모 연료 * 2 충전
 */