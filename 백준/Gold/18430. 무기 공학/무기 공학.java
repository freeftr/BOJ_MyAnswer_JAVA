import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int max = 0;

    // 4가지 부메랑 형태 (중심, 팔1, 팔2 상대좌표)
    static int[][][] shape = {
            {{0,0},{0,1},{1,0}},   // ㄱ
            {{0,0},{-1,0},{0,1}},  // ㄴ
            {{0,0},{0,-1},{1,0}},  // ㄴ 반대
            {{0,0},{0,-1},{-1,0}}  // ㄱ 반대
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);
        System.out.println(max);
    }

    static void dfs(int idx, int sum) {
        if (idx == N * M) {
            max = Math.max(max, sum);
            return;
        }

        int x = idx / M;
        int y = idx % M;

        // 이미 방문된 칸이면 다음 칸으로 넘어감
        if (visited[x][y]) {
            dfs(idx + 1, sum);
            return;
        }

        // 4개 부메랑 형태 시도
        for (int[][] s : shape) {
            int cx = x + s[0][0];
            int cy = y + s[0][1];

            int ax = x + s[1][0];
            int ay = y + s[1][1];

            int bx = x + s[2][0];
            int by = y + s[2][1];

            // 범위 체크
            if (!in(cx, cy) || !in(ax, ay) || !in(bx, by)) continue;

            // 방문 체크
            if (visited[cx][cy] || visited[ax][ay] || visited[bx][by]) continue;

            // 방문 처리
            visited[cx][cy] = visited[ax][ay] = visited[bx][by] = true;

            int add = map[cx][cy] * 2 + map[ax][ay] + map[bx][by];
            dfs(idx + 1, sum + add);

            // 복귀
            visited[cx][cy] = visited[ax][ay] = visited[bx][by] = false;
        }

        // 부메랑을 만들지 않고 넘어가는 경우
        dfs(idx + 1, sum);
    }

    static boolean in(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
