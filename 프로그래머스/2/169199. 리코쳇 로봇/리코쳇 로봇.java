import java.util.*;

class Solution {

    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static char[][] map;
    static int rx, ry, gx, gy;
    static int result = Integer.MAX_VALUE;

    public int solution(String[] board) {
        map = new char[board.length][board[0].length()];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                map[i][j] = board[i].charAt(j);
                if (map[i][j] == 'R') {
                    rx = i;
                    ry = j;
                }
                if (map[i][j] == 'G') {
                    gx = i;
                    gy = j;
                }
            }
        }

        bfs();
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[map.length][map[0].length];
        visited[rx][ry] = true;
        q.add(new int[]{rx, ry, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];

            if (x == gx && y == gy) {
                result = Math.min(result, dist);
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x;
                int ny = y;

                while (true) {
                    int tx = nx + dx[d];
                    int ty = ny + dy[d];

                    if (tx < 0 || ty < 0 || tx >= map.length || ty >= map[0].length || map[tx][ty] == 'D') break;
                    nx = tx;
                    ny = ty;
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, dist + 1});
                }
            }
        }
    }
}
