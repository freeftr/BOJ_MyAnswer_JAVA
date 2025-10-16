import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static boolean[][] sizeCheck;
    static int[][] unions;
    static HashMap<Integer, Integer> sizes = new HashMap<>();
    static int[][] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        answer = new int[N][M];
        unions = new int[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int idx = 1;
        sizeCheck = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!sizeCheck[i][j] && board[i][j] == '0') {
                    sizes.put(idx, getSize(i, j, board, idx));
                    idx++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == '1') {
                    answer[i][j] = (getNear(i, j, board) + 1) % 10;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(answer[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static int getNear(int a, int b, char[][] board) {
        int sum = 0;
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < 4; i++) {
            int nx = a + dx[i];
            int ny = b + dy[i];

            if (outRange(nx, ny)) continue;
            if (board[nx][ny] == '1') continue;

            set.add(unions[nx][ny]);
        }

        for (int num : set) {
            sum += sizes.get(num);
        }

        return sum;
    }

    static int getSize(int a, int b, char[][] board, int idx) {
        Queue<int[]> q = new ArrayDeque<>();
        sizeCheck[a][b] = true;
        q.add(new int[]{a, b});

        int size = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            unions[x][y] = idx;

            size++;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (outRange(nx, ny)) continue;
                if (sizeCheck[nx][ny]) continue;
                if (board[nx][ny] == '1') continue;

                sizeCheck[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

        return size;
    }

    static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= M;
    }
}

/*
조건
- 벽부수고 이동 가능.

요구
- 벽을 부수고 이동할 수 있는 곳으로 변경한다.
- 그 위치에서 이동할 수 있는 칸의 개수를 세어본다.

풀이
- 0인 곳별로 크기 캐싱 (각 컴포넌트에 고유 idx 라벨)
- 1인 곳은 인접한 서로 다른 컴포넌트의 사이즈 합 + 1을 10으로 나눈 값
*/
