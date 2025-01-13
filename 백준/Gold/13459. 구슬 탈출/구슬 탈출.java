import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static char[][] board;
    static int red_x = 0, red_y = 0, blue_x = 0, blue_y = 0;
    static int hole_x = 0, hole_y = 0;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
                if (board[i][j] == 'R') {
                    red_x = i;
                    red_y = j;
                } else if (board[i][j] == 'B') {
                    blue_x = i;
                    blue_y = j;
                } else if (board[i][j] == 'O') {
                    hole_x = i;
                    hole_y = j;
                }
            }
        }

        System.out.println(bfs());
    }

    static int bfs() {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][N][M];
        queue.add(new int[]{red_x, red_y, blue_x, blue_y, 1});
        visited[red_x][red_y][blue_x][blue_y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int rx = current[0], ry = current[1], bx = current[2], by = current[3], count = current[4];

            if (count > 10) return 0;

            for (int i = 0; i < 4; i++) {
                int[] next = move(rx, ry, bx, by, i);
                int nrx = next[0], nry = next[1], nbx = next[2], nby = next[3];

                if (nbx == hole_x && nby == hole_y) continue;
                if (nrx == hole_x && nry == hole_y) return 1;

                if (nrx == nbx && nry == nby) {
                    if (distance(rx, ry, nrx, nry) > distance(bx, by, nbx, nby)) {
                        nrx -= dx[i];
                        nry -= dy[i];
                    } else {
                        nbx -= dx[i];
                        nby -= dy[i];
                    }
                }

                if (!visited[nrx][nry][nbx][nby]) {
                    visited[nrx][nry][nbx][nby] = true;
                    queue.add(new int[]{nrx, nry, nbx, nby, count + 1});
                }
            }
        }
        return 0;
    }

    static int[] move(int rx, int ry, int bx, int by, int dir) {
        int nrx = rx, nry = ry, nbx = bx, nby = by;

        while (board[nrx + dx[dir]][nry + dy[dir]] != '#' && board[nrx][nry] != 'O') {
            nrx += dx[dir];
            nry += dy[dir];
        }

        while (board[nbx + dx[dir]][nby + dy[dir]] != '#' && board[nbx][nby] != 'O') {
            nbx += dx[dir];
            nby += dy[dir];
        }

        return new int[]{nrx, nry, nbx, nby};
    }

    static int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}