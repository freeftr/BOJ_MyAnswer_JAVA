import java.util.*;

class Solution {
    static HashSet<Integer> cardTypes = new HashSet<>();
    static ArrayList<int[]> perms = new ArrayList<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public int solution(int[][] board, int r, int c) {
        int answer = Integer.MAX_VALUE;

        cardTypes.clear();
        perms.clear();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) cardTypes.add(board[i][j]);
            }
        }

        int k = cardTypes.size();
        makePer(0, new int[k], new boolean[k + 1]);

        for (int[] perm : perms) {
            int[][] temp = copy(board);
            int cost = solve(temp, perm, 0, r, c);
            answer = Math.min(answer, cost);
        }

        return answer;
    }

    static int solve(int[][] board, int[] perm, int depth, int r, int c) {
        if (depth == perm.length) return 0;

        int idx = perm[depth];

        int[] p1 = null, p2 = null;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == idx) {
                    if (p1 == null) p1 = new int[]{i, j};
                    else p2 = new int[]{i, j};
                }
            }
        }

        if (p1 == null || p2 == null) return solve(board, perm, depth + 1, r, c);

        int[][] distCur = bfsDist(board, r, c);
        int[][] distP1 = bfsDist(board, p1[0], p1[1]);
        int[][] distP2 = bfsDist(board, p2[0], p2[1]);

        int toP1 = distCur[p1[0]][p1[1]];
        int toP2 = distCur[p2[0]][p2[1]];
        int p1ToP2 = distP1[p2[0]][p2[1]];
        int p2ToP1 = distP2[p1[0]][p1[1]];

        int best = Integer.MAX_VALUE;

        {
            int cost = toP1 + 1 + p1ToP2 + 1;
            int a = board[p1[0]][p1[1]];
            int b = board[p2[0]][p2[1]];
            board[p1[0]][p1[1]] = 0;
            board[p2[0]][p2[1]] = 0;

            best = Math.min(best, cost + solve(board, perm, depth + 1, p2[0], p2[1]));

            board[p1[0]][p1[1]] = a;
            board[p2[0]][p2[1]] = b;
        }

        {
            int cost = toP2 + 1 + p2ToP1 + 1;
            int a = board[p1[0]][p1[1]];
            int b = board[p2[0]][p2[1]];
            board[p1[0]][p1[1]] = 0;
            board[p2[0]][p2[1]] = 0;

            best = Math.min(best, cost + solve(board, perm, depth + 1, p1[0], p1[1]));

            board[p1[0]][p1[1]] = a;
            board[p2[0]][p2[1]] = b;
        }

        return best;
    }

    static int[][] bfsDist(int[][] board, int sr, int sc) {
        int[][] dist = new int[4][4];
        for (int i = 0; i < 4; i++) Arrays.fill(dist[i], -1);

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});
        dist[sr][sc] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (!checkRange(nx, ny) && dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    q.add(new int[]{nx, ny});
                }

                int[] cm = ctrlMove(board, x, y, dir);
                nx = cm[0];
                ny = cm[1];
                if (dist[nx][ny] == -1) {
                    dist[nx][ny] = dist[x][y] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        return dist;
    }

    static int[] ctrlMove(int[][] board, int x, int y, int dir) {
        int nx = x, ny = y;
        while (true) {
            int tx = nx + dx[dir];
            int ty = ny + dy[dir];

            if (checkRange(tx, ty)) break;
            nx = tx;
            ny = ty;

            if (board[nx][ny] != 0) break;
        }
        return new int[]{nx, ny};
    }

    static void makePer(int depth, int[] selected, boolean[] visited) {
        int k = selected.length;
        if (depth == k) {
            perms.add(Arrays.copyOf(selected, selected.length));
            return;
        }

        for (int i = 1; i <= k; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            selected[depth] = i;
            makePer(depth + 1, selected, visited);
            visited[i] = false;
        }
    }

    static int[][] copy(int[][] board) {
        int[][] temp = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(board[i], 0, temp[i], 0, 4);
        }
        return temp;
    }

    static boolean checkRange(int x, int y) {
        return x < 0 || y < 0 || x >= 4 || y >= 4;
    }
}
