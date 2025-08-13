import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static boolean[][] visited = new boolean[3][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            char[][] map = new char[3][3];
            visited = new boolean[3][3];

            for (int i = 0; i < 3; i++) {
                String s = br.readLine();
                for (int j = 0; j < 3; j++) {
                    map[i][j] = s.charAt(j);
                }
            }

            String[] s = br.readLine().split(" ");
            int n = Integer.parseInt(s[0]);
            ArrayList<Integer> list = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                list.add(Integer.parseInt(s[i + 1]));
            }

            ArrayList<Integer> result = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if ((i == 1 && j == 1) || map[i][j] != 'O' || visited[i][j]) continue;

                    result.add(bfs(i, j, map));
                }
            }

            Collections.sort(result);
            Collections.sort(list);

            if (n != result.size()) {
                System.out.println(0);
                continue;
            }

            boolean ok = true;
            for (int i = 0; i < n; i++) {
                if (!result.get(i).equals(list.get(i))) {
                    ok = false;
                    break;
                }
            }
            System.out.println(ok ? 1 : 0);
        }
    }

    static int bfs(int a, int b, char[][] map) {
        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[]{a, b});
        visited[a][b] = true;

        int size = 1;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3) continue;
                if (nx == 1 && ny == 1) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] != 'O') continue;

                q.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                size++;
            }
        }

        return size;
    }
}