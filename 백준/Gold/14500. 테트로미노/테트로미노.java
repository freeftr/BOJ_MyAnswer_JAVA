import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][][] d = {
            {{0, 0}, {0, 1}, {0, 2}, {0, 3}},
            {{0, 0}, {0, 1}, {1, 0}, {1, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}},
            {{0, 0}, {1, 0}, {1, 1}, {2, 1}},
            {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {2, -1}},
            {{0, 0}, {1, 0}, {2, -1}, {1, -1}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        for (int a = 0; a < 4; a++) {
            answer = Math.max(answer, getMax());
            turn();
        }

        System.out.println(answer);
    }

    static int getMax() {
        int result = 0;

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                for (int[][] shape : d) {
                    int sum = 0;
                    boolean ok = true;

                    for (int[] pos : shape) {
                        int nx = x + pos[0];
                        int ny = y + pos[1];

                        if (checkRange(nx, ny)) { ok = false; break; }
                        sum += map[nx][ny];
                    }

                    if (ok) result = Math.max(result, sum);
                }
            }
        }

        return result;
    }

    static boolean checkRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }

    static void turn() {
        int[][] temp = new int[M][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[j][N - 1 - i] = map[i][j];
            }
        }

        map = temp;

        int oldN = N;
        N = M;
        M = oldN;
    }

}