import java.util.*;

class Solution {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];

        for (int i = 0; i < places.length; i++) {
            String[] map = places[i];
            boolean ok = true;

            for (int a = 0; a < 5 && ok; a++) {
                for (int b = 0; b < 5 && ok; b++) {
                    if (map[a].charAt(b) == 'P') {
                        if (!bfs(a, b, map)) ok = false;
                    }
                }
            }
            answer[i] = ok ? 1 : 0;
        }
        return answer;
    }

    static boolean bfs(int a, int b, String[] map) {
        boolean[][] visited = new boolean[5][5];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        visited[a][b] = true;
        q.add(new int[]{a, b});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1];
            int dist = Math.abs(a - x) + Math.abs(b - y);

            if (dist > 2) continue;
            if (dist > 0 && map[x].charAt(y) == 'P') return false;
            if (dist == 2) continue;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                if (visited[nx][ny]) continue;
                if (map[nx].charAt(ny) == 'X') continue;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
        return true;
    }
}
