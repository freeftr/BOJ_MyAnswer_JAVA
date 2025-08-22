import java.util.*;

class Solution {

    static int N;
    static int[][] MAP;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public int solution(int[][] board) {
        N = board.length;
        MAP = board;
        return bfs();
    }

    static int bfs() {
        Queue<Robot> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][N][2];

        q.add(new Robot(0, 0, 0, 1, 0, 0));

        while (!q.isEmpty()) {
            Robot v = q.poll();

            if ((v.lx == N - 1 && v.ly == N - 1) || (v.rx == N - 1 && v.ry == N - 1)) {
                return v.cnt;
            }

            if (visited[v.lx][v.ly][v.dir]) continue;
            visited[v.lx][v.ly][v.dir] = true;

            for (int i = 0; i < 4; i++) {
                int nlx = v.lx + dx[i];
                int nly = v.ly + dy[i];
                int nrx = v.rx + dx[i];
                int nry = v.ry + dy[i];

                if (!check(nlx, nly, nrx, nry)) continue;

                if (v.dir == 0) {
                    if (nly <= nry) {
                        q.add(new Robot(nlx, nly, nrx, nry, 0, v.cnt + 1));
                    } else {
                        q.add(new Robot(nrx, nry, nlx, nly, 0, v.cnt + 1));
                    }
                } else {
                    if (nlx <= nrx) {
                        q.add(new Robot(nlx, nly, nrx, nry, 1, v.cnt + 1));
                    } else {
                        q.add(new Robot(nrx, nry, nlx, nly, 1, v.cnt + 1));
                    }
                }
            }

            if (v.dir == 0) {
                if (v.lx - 1 >= 0 && MAP[v.lx - 1][v.ly] == 0 && MAP[v.rx - 1][v.ry] == 0) {
                    q.add(new Robot(v.lx - 1, v.ly, v.lx, v.ly, 1, v.cnt + 1));
                    q.add(new Robot(v.rx - 1, v.ry, v.rx, v.ry, 1, v.cnt + 1));
                }
                if (v.lx + 1 < N && MAP[v.lx + 1][v.ly] == 0 && MAP[v.rx + 1][v.ry] == 0) {
                    q.add(new Robot(v.lx, v.ly, v.lx + 1, v.ly, 1, v.cnt + 1));
                    q.add(new Robot(v.rx, v.ry, v.rx + 1, v.ry, 1, v.cnt + 1));
                }
            } else {
                if (v.ly - 1 >= 0 && MAP[v.lx][v.ly - 1] == 0 && MAP[v.rx][v.ry - 1] == 0) {
                    q.add(new Robot(v.lx, v.ly - 1, v.lx, v.ly, 0, v.cnt + 1));
                    q.add(new Robot(v.rx, v.ry - 1, v.rx, v.ry, 0, v.cnt + 1));
                }
                if (v.ly + 1 < N && MAP[v.lx][v.ly + 1] == 0 && MAP[v.rx][v.ry + 1] == 0) {
                    q.add(new Robot(v.lx, v.ly, v.lx, v.ly + 1, 0, v.cnt + 1));
                    q.add(new Robot(v.rx, v.ry, v.rx, v.ry + 1, 0, v.cnt + 1));
                }
            }
        }

        return -1;
    }

    static boolean check(int nlx, int nly, int nrx, int nry) {
        if (nlx < 0 || nly < 0 || nlx >= N || nly >= N) return false;
        if (nrx < 0 || nry < 0 || nrx >= N || nry >= N) return false;
        if (MAP[nlx][nly] == 1 || MAP[nrx][nry] == 1) return false;
        return true;
    }

    static class Robot {
        int lx, ly;
        int rx, ry;
        int dir;
        int cnt;

        Robot(int lx, int ly, int rx, int ry, int dir, int cnt) {
            this.lx = lx;
            this.ly = ly;
            this.rx = rx;
            this.ry = ry;
            this.dir = dir;
            this.cnt = cnt;
        }
    }
}
