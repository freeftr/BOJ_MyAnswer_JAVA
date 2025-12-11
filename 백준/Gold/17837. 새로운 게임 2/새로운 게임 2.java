import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int N, K;
    static int[][] mapInfo; // 0: 흰, 1: 빨강, 2: 파랑
    static String[][] map; // 각 칸에 쌓인 말들
    static HashMap<Integer, int[]> pos = new HashMap<>();
    static HashMap<Integer, Integer> directions = new HashMap<>();

    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new String[N + 1][N + 1];
        mapInfo = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = "";
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                mapInfo[i + 1][j + 1] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            directions.put(i, dir);
            pos.put(i, new int[]{x, y});
            map[x][y] = map[x][y] + i + " ";
        }

        for (int turn = 1; turn <= 1000; turn++) {

            for (int idx = 0; idx < K; idx++) {
                int x = pos.get(idx)[0];
                int y = pos.get(idx)[1];
                int dir = directions.get(idx);

                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 1. 범위 밖이거나 파란색이면 먼저 방향 반전
                if (nx < 1 || nx > N || ny < 1 || ny > N || mapInfo[nx][ny] == 2) {
                    if (dir == 1) dir = 2;
                    else if (dir == 2) dir = 1;
                    else if (dir == 3) dir = 4;
                    else if (dir == 4) dir = 3;

                    directions.put(idx, dir);

                    nx = x + dx[dir];
                    ny = y + dy[dir];

                    if (nx < 1 || nx > N || ny < 1 || ny > N || mapInfo[nx][ny] == 2) {
                        continue;
                    }
                }

                StringBuilder move = parseInfo(x, y, idx);
                String moveStr = move.toString();

                int tile = mapInfo[nx][ny];

                if (tile == 1) {
                    String trimmed = moveStr.trim();
                    String[] arr = trimmed.split(" ");
                    StringBuilder rev = new StringBuilder();
                    for (int i = arr.length - 1; i >= 0; i--) {
                        rev.append(arr[i]).append(" ");
                    }
                    moveStr = rev.toString();
                }

                map[nx][ny] = map[nx][ny] + moveStr;

                String[] moved = moveStr.trim().split(" ");
                for (String s : moved) {
                    int num = Integer.parseInt(s);
                    pos.put(num, new int[]{nx, ny});
                }

                if (map[nx][ny].trim().length() > 0) {
                    int count = map[nx][ny].trim().split(" ").length;
                    if (count >= 4) {
                        System.out.println(turn);
                        return;
                    }
                }
            }
        }

        System.out.println(-1);
    }

    static StringBuilder parseInfo(int x, int y, int idx) {
        String info = map[x][y]; // 예: "3 1 0 "
        String[] arr = info.trim().split(" ");

        boolean flag = true;
        StringBuilder stay = new StringBuilder();
        StringBuilder move = new StringBuilder();

        for (String s : arr) {
            int now = Integer.parseInt(s);
            if (now == idx) flag = false;

            if (flag) {
                stay.append(now).append(" ");
            } else {
                move.append(now).append(" ");
            }
        }

        map[x][y] = stay.toString();
        return move;
    }
}
