import java.io.*;
import java.util.*;

public class Main {
    static char[][] map = new char[5][5];
    static boolean[][] visited = new boolean[5][5];
    static int answer = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < 5; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        combination(0, 0, new int[7][2]);
        System.out.println(answer);
    }

    static void combination(int start, int depth, int[][] comb) {
        if (depth == 7) {
            if (checkS(comb) && isConnected(comb)) {
                answer++;
            }
            return;
        }

        for (int i = start; i < 25; i++) {
            int x = i / 5;
            int y = i % 5;
            comb[depth][0] = x;
            comb[depth][1] = y;
            combination(i + 1, depth + 1, comb);
        }
    }

    static boolean checkS(int[][] comb) {
        int sCount = 0;
        for (int i = 0; i < 7; i++) {
            int x = comb[i][0];
            int y = comb[i][1];
            if (map[x][y] == 'S') {
                sCount++;
            }
        }
        return sCount >= 4;
    }

    static boolean isConnected(int[][] comb) {
        boolean[][] selected = new boolean[5][5];
        for (int i = 0; i < 7; i++) {
            selected[comb[i][0]][comb[i][1]] = true;
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];
        q.add(new int[]{comb[0][0], comb[0][1]});
        visited[comb[0][0]][comb[0][1]] = true;
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= 5 || ny >= 5) continue;
                if (visited[nx][ny]) continue;
                if (!selected[nx][ny]) continue;

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
                cnt++;
            }
        }

        return cnt == 7;
    }
}
