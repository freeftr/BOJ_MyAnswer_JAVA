import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][][] origin = new int[5][5][5];
    static int[][][] board = new int[5][5][5];
    static int[] order = {0, 1, 2, 3, 4};

    static int[] dx = {0, 0, 0, 0, 1, -1};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {1, -1, 0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++) {
                    origin[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        do {
            for (int a = 0; a < 4; a++) {
                for (int b = 0; b < 4; b++) {
                    for (int c = 0; c < 4; c++) {
                        for (int d = 0; d < 4; d++) {
                            for (int e = 0; e < 4; e++) {

                                build(new int[]{a, b, c, d, e});

                                int res = searchRoute(0, 0, 0, 4, 4, 4);
                                answer = Math.min(answer, res);

                                if (answer == 12) {
                                    System.out.println(12);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        } while (nextPermutation(order));

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void build(int[] rot) {
        for (int z = 0; z < 5; z++) {
            int[][] temp = origin[order[z]];
            for (int r = 0; r < rot[z]; r++) {
                temp = rotate(temp);
            }
            board[z] = temp;
        }
    }

    static int[][] rotate(int[][] src) {
        int[][] ret = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                ret[j][4 - i] = src[i][j];
            }
        }
        return ret;
    }

    static int searchRoute(int sx, int sy, int sz, int ex, int ey, int ez) {

        if (board[sz][sx][sy] == 0 || board[ez][ex][ey] == 0)
            return Integer.MAX_VALUE;

        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[5][5][5];

        q.add(new int[]{sx, sy, sz, 0});
        visited[sz][sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int z = cur[2];
            int dist = cur[3];

            if (x == ex && y == ey && z == ez)
                return dist;

            for (int i = 0; i < 6; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];

                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5 || nz < 0 || nz >= 5)
                    continue;
                if (visited[nz][nx][ny])
                    continue;
                if (board[nz][nx][ny] == 0)
                    continue;

                visited[nz][nx][ny] = true;
                q.add(new int[]{nx, ny, nz, dist + 1});
            }
        }

        return Integer.MAX_VALUE;
    }

    static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) i--;
        if (i == 0) return false;

        int j = arr.length - 1;
        while (arr[i - 1] >= arr[j]) j--;

        swap(arr, i - 1, j);

        int k = arr.length - 1;
        while (i < k) swap(arr, i++, k--);

        return true;
    }

    static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}

/*
조건
- 3차원 미로
- 5 X 5 판 5개 쌓기
- 판들은 시계 반시계로 회전 가능
- 가장 적은 횟수로 탈출하기

요구
- 미로를 탈출하는 가장 적은 이동 횟수 구하기
- 탈출 불가 시 -1 출력

풀이
1. 일단 모든 경우의 수 구하기 (4 * 4 * 4 * 4 * 4)
2. bfs로 최단 경로 탐색

0   0   0   0   0
0   0   0   1   0
0   0   0   0   0
0   0   0   0   0
0   0   0   0   0

0   0   0   0   0
0   0   0   0   0
0   0   0   0   0
0   0   0   1   0
0   0   0   0   0

0   0   0   0   0
0   0   0   0   0
0   0   0   0   0
0   1   0   0   0
0   0   0   0   0

n = 4
1,3 -> 3,3
3,3 -> 3,1


 */