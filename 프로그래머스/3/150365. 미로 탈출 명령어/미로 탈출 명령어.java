class Solution {
    // d, l, r, u => 사전순
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static char[] dir = {'d', 'l', 'r', 'u'};
    static StringBuilder result;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        result = new StringBuilder();
        if (dfs(n, m, x, y, r, c, k, new StringBuilder())) {
            return result.toString();
        } else {
            return "impossible";
        }
    }

    static boolean dfs(int n, int m, int x, int y, int r, int c, int k, StringBuilder path) {
        // 목표 지점까지 남은 거리 계산
        int dist = Math.abs(x - r) + Math.abs(y - c);

        // 가지치기
        if (dist > k || (k - dist) % 2 != 0) {
            return false;
        }

        if (k == 0) {
            if (x == r && y == c) {
                result = new StringBuilder(path);
                return true;
            }
            return false;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 1 || ny < 1 || nx > n || ny > m) continue;

            path.append(dir[i]);
            if (dfs(n, m, nx, ny, r, c, k - 1, path)) {
                return true; 
            }
            path.deleteCharAt(path.length() - 1);
        }

        return false;
    }
}
