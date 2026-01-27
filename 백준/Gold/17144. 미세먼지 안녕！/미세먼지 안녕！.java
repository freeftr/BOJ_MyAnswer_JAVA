import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int R, C, T;
    static int[][] board;
    static int r = -1, c = -1;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == -1) {
                    r = i;
                    c = j;
                }
            }
        }

        for (int t = 0; t < T; t++) {
            expansion();
            activate();
        }

        System.out.println(calculate());
    }

    static void activate() {
        for (int x = r - 1; x >= 2; x--) {
            board[x][1] = board[x - 1][1];
        }

        for (int y = 1; y <= C - 1; y++) {
            board[1][y] = board[1][y + 1];
        }

        for (int x = 1; x <= r - 2; x++) {
            board[x][C] = board[x + 1][C];
        }

        for (int y = C; y >= 2; y--) {
            board[r - 1][y] = board[r - 1][y - 1];
        }

        for (int x = r; x <= R - 1; x++) {
            board[x][1] = board[x + 1][1];
        }

        for (int y = 1; y <= C - 1; y++) {
            board[R][y] = board[R][y + 1];
        }

        for (int x = R; x >= r + 1; x--) {
            board[x][C] = board[x - 1][C];
        }

        for (int y = C; y >= 2; y--) {
            board[r][y] = board[r][y - 1];
        }

        board[r - 1][2] = 0;
        board[r][2] = 0;

        board[r - 1][1] = -1;
        board[r][1] = -1;
    }

    static void expansion() {
        int[][] temp = new int[R + 1][C + 1];

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (board[i][j] > 0) {
                    int A = board[i][j];
                    int cnt = 0;

                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if (checkRange(nx, ny)) continue;
                        if (board[nx][ny] == -1) continue;

                        cnt++;
                        temp[nx][ny] += A / 5;
                    }

                    temp[i][j] += A - (A / 5) * cnt;
                }
            }
        }

        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                board[i][j] = temp[i][j];
            }
        }

        board[r - 1][1] = -1;
        board[r][1] = -1;
    }

    static int calculate() {
        int sum = 0;
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (board[i][j] > 0) sum += board[i][j];
            }
        }
        return sum;
    }

    static boolean checkRange(int x, int y) {
        return x <= 0 || y <= 0 || x > R || y > C;
    }
}


/*
조건
- 1-based
1. 먼지 확산: 인접 네 방향 => + A / 5, 기존 칸 => A / 5
    - 동시에 일어난다.
    - 공청있는 부분은 제외
2. 작동: 위쪽은 반시계, 아래쪽은 시계
    - 바람 방향대로 1칸씩 이동
    - 공청으로 들어간 미세먼지는 제거

요구
- T초 후 남아있는 미세먼지 양 구하기
 */