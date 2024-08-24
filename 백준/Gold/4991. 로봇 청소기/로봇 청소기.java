import java.util.*;
import java.io.*;

public class Main {
    static int w, h;
    static char[][] room;
    static int[][] distances;
    static int ans;
    static boolean[] isSelected;
    static int start_x, start_y;

    static ArrayList<int[]> dirty;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;

            room = new char[h][w];
            dirty = new ArrayList<>();
            ans = Integer.MAX_VALUE;

            for (int i = 0; i < h; i++) {
                String s = br.readLine();
                for (int j = 0; j < w; j++) {
                    room[i][j] = s.charAt(j);
                    if (room[i][j] == 'o') {
                        start_x = i;
                        start_y = j;
                    }
                    if (room[i][j] == '*') {
                        dirty.add(new int[]{i, j});
                    }
                }
            }

            int size = dirty.size();
            distances = new int[size + 1][size + 1];
            for (int i = 0; i <= size; i++) {
                Arrays.fill(distances[i], -1);
            }

            // 모든 더러운 위치와 시작 위치 간의 거리 계산
            int[][] points = new int[size + 1][2];
            points[0][0] = start_x;
            points[0][1] = start_y;
            for (int i = 0; i < size; i++) {
                points[i + 1] = dirty.get(i);
            }

            for (int i = 0; i <= size; i++) {
                for (int j = i + 1; j <= size; j++) {
                    int dist = bfs(points[i][0], points[i][1], points[j][0], points[j][1]);
                    if (dist != -1) {
                        distances[i][j] = dist;
                        distances[j][i] = dist;
                    }
                }
            }

            isSelected = new boolean[size];
            dfs(0, 0, size, 0);

            if (ans == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(ans);
            }
        }
    }

    public static void dfs(int current, int depth, int size, int dist) {
        if (dist >= ans) return;

        if (depth == size) {
            ans = Math.min(ans, dist);
            return;
        }

        for (int i = 1; i <= size; i++) {
            if (isSelected[i - 1]) continue;
            isSelected[i - 1] = true;
            int temp = distances[current][i];
            if (temp != -1) {
                dfs(i, depth + 1, size, dist + temp);
            }
            isSelected[i - 1] = false;
        }
    }

    public static int bfs(int sx, int sy, int ex, int ey) {
        boolean[][] visited = new boolean[h][w];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] arr = q.poll();
            int x = arr[0];
            int y = arr[1];
            int dist = arr[2];
            if (x == ex && y == ey) {
                return dist;
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= h || ny >= w) continue;
                if (room[nx][ny] == 'x' || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, dist + 1});
            }
        }
        return -1;
    }
}