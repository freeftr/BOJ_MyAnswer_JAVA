import java.io.*;
import java.util.*;

public class Main {

    static char[][] board;
    static int h, w;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            board = new char[h + 2][w + 2];
            for (int i = 0; i < h + 2; i++) Arrays.fill(board[i], '.');

            for (int i = 1; i <= h; i++) {
                String line = br.readLine();
                for (int j = 1; j <= w; j++) {
                    board[i][j] = line.charAt(j - 1);
                }
            }

            String s = br.readLine().trim();
            boolean[] hasKey = new boolean[26];
            if (!s.equals("0")) {
                for (char c : s.toCharArray()) hasKey[c - 'a'] = true;
            }

            System.out.println(bfs(hasKey));
        }
    }

    static int bfs(boolean[] hasKey) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[h + 2][w + 2];

        ArrayList<int[]>[] waitDoors = new ArrayList[26];
        for (int i = 0; i < 26; i++) waitDoors[i] = new ArrayList<>();

        q.add(new int[]{0, 0});
        visited[0][0] = true;

        int answer = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= h + 2 || ny >= w + 2) continue;
                if (visited[nx][ny]) continue;
                if (board[nx][ny] == '*') continue;

                char cell = board[nx][ny];

                if (cell == '$') {
                    answer++;
                    board[nx][ny] = '.';
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    continue;
                }

                if (cell >= 'a' && cell <= 'z') {
                    int k = cell - 'a';
                    if (!hasKey[k]) {
                        hasKey[k] = true;
                        for (int[] pos : waitDoors[k]) {
                            if (!visited[pos[0]][pos[1]]) {
                                visited[pos[0]][pos[1]] = true;
                                q.add(new int[]{pos[0], pos[1]});
                            }
                        }
                        waitDoors[k].clear();
                    }
                    board[nx][ny] = '.';
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    continue;
                }

                if (cell >= 'A' && cell <= 'Z') {
                    int k = cell - 'A';
                    if (!hasKey[k]) {
                        waitDoors[k].add(new int[]{nx, ny});
                        continue;
                    }
                    board[nx][ny] = '.';
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                    continue;
                }

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

        return answer;
    }
}
