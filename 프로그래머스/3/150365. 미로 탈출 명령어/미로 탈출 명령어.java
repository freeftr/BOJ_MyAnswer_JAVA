class Solution {
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static char[] dir = {'d', 'l', 'r', 'u'};
    static int N, M;
    static String result = "";

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n; M = m;
        x--; y--; r--; c--;

        if (dfs(x, y, new StringBuilder(), 0, r, c, k)) {
            return result;
        } else {
            return "impossible";
        }
    }

    static boolean dfs(int x, int y, StringBuilder sb, int dist, int r, int c, int k) {
        if (dist > k) return false;
        
        if (Math.abs(x - r) + Math.abs(y - c) > k - dist) return false;
        if ((Math.abs(x - r) + Math.abs(y - c)) % 2 != (k - dist) % 2) return false;

        if (dist == k && x == r && y == c) {
            result = sb.toString();
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

            sb.append(dir[i]);
            if (dfs(nx, ny, sb, dist + 1, r, c, k)) return true;
            sb.deleteCharAt(sb.length() - 1);
        }
        return false;
    }
}
