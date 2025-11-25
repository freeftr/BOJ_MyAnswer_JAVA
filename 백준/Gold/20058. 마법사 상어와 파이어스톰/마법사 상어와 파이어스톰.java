import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, Q;
    static int size;
    static int[][] A;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        size = getMulti(N);
        A = new int[size][size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] cmds = new int[Q];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            if (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            cmds[i] = Integer.parseInt(st.nextToken());
        }

        for (int cmd : cmds) {
            int cur = getMulti(cmd);
            turn(cur);
            melt();
        }

        int biggest = getBiggest();
        int sum = sum();

        System.out.println(sum);
        System.out.println(biggest);
    }

    static int getBiggest() {
        boolean[][] visited = new boolean[size][size];
        int result = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (visited[i][j]) continue;
                if (A[i][j] == 0) continue;

                Queue<int[]> q = new ArrayDeque<>();
                q.add(new int[]{i, j});
                visited[i][j] = true;

                int cnt = 0;

                while (!q.isEmpty()) {
                    int[] cur = q.poll();
                    int x = cur[0];
                    int y = cur[1];
                    cnt++;

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];

                        if (nx < 0 || ny < 0 || nx >= size || ny >= size) continue;
                        if (visited[nx][ny]) continue;
                        if (A[nx][ny] == 0) continue;

                        q.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }

                result = Math.max(result, cnt);
            }
        }

        return result;
    }

    static int sum() {
        int result = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                result += A[x][y];
            }
        }
        return result;
    }

    static void melt() {
        int[][] next = new int[size][size];

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (A[x][y] == 0) {
                    next[x][y] = 0;
                    continue;
                }

                int cnt = 0;
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= size || ny >= size) continue;
                    if (A[nx][ny] > 0) cnt++;
                }

                if (cnt < 3) next[x][y] = A[x][y] - 1;
                else next[x][y] = A[x][y];
            }
        }

        A = next;
    }

    static int getMulti(int v) {
        int result = 1;
        for (int i = 0; i < v; i++) result *= 2;
        return result;
    }

    static void turn(int n) {
        int[][] temp = new int[size][size];

        for (int i = 0; i < size; i += n) {
            for (int j = 0; j < size; j += n) {
                for (int a = 0; a < n; a++) {
                    for (int b = 0; b < n; b++) {
                        temp[i + b][j + (n - 1 - a)] = A[i + a][j + b];
                    }
                }
            }
        }

        for (int i = 0; i < size; i++) {
            System.arraycopy(temp[i], 0, A[i], 0, size);
        }
    }
}
