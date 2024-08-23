import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T;
    static int[][] onepann;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        onepann = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                onepann[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            turn(x, d, k);

            if(!remove()){
                avg();
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(onepann[i][j] != 0)  ans += onepann[i][j];
            }
        }
        System.out.println(ans);
    }

    // 회전 로직
    public static void turn(int x, int d, int k) {
        for (int i = 0; i < N; i++) {
            if ((i+1) % x == 0) {
                // 시계 방향
                if (d == 1) {
                    int[] temp = new int[M];
                    for (int j = 0; j < M; j++) {
                        temp[j] = onepann[i][(j + k) % M];
                    }
                    onepann[i] = temp;
                }
                // 반시계 방향
                else {
                    int[] temp = new int[M];
                    for (int j = 0; j < M; j++) {
                        temp[j] = onepann[i][(j + M - k) % M];
                    }
                    onepann[i] = temp;
                }
            }
        }
    }

    // 인접 수 삭제
    public static boolean remove() {
        boolean check = false;
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (onepann[i][j] == 0) continue;
                if (visited[i][j]) continue;

                Queue<int[]> q = new LinkedList<>();
                q.add(new int[]{i, j, onepann[i][j]});

                while (!q.isEmpty()) {
                    int[] arr = q.poll();
                    int X = arr[0];
                    int Y = arr[1];
                    int target = arr[2];

                    for (int n = 0; n < 4; n++) {
                        int nx = X + dx[n];
                        int ny = Y + dy[n];

                        if (nx < 0 || nx >= N) continue;
                        if (ny == -1) ny = M - 1;
                        if (ny == M) ny = 0;

                        if (visited[nx][ny]) continue;
                        if (onepann[nx][ny] == target) {
                            q.add(new int[]{nx, ny, target});
                            visited[nx][ny] = true;
                            onepann[nx][ny] = 0;
                            check = true;
                        }
                    }
                }
            }
        }

        return check;
    }

    // 평균 비교
    public static void avg() {
        int sum = 0, cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (onepann[i][j] != 0) {
                    cnt++;
                    sum += onepann[i][j];
                }
            }
        }
        if (cnt > 0) {
            double avg = (double) sum / cnt;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (onepann[i][j] != 0) {
                        if (onepann[i][j] > avg) {
                            onepann[i][j]--;
                        } else if (onepann[i][j] < avg) {
                            onepann[i][j]++;
                        }
                    }
                }
            }
        }
    }
}