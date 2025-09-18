import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r - 1][c - 1] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        String[] cmds = new String[L];
        for (int i = 0; i < L; i++) {
            cmds[i] = br.readLine();
        }

        ArrayDeque<int[]> dq = new ArrayDeque<>();
        HashSet<String> set = new HashSet<>();
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        dq.addFirst(new int[]{0, 0});
        set.add("0 0");

        int time = 0;
        int dir = 0;

        for (int i = 0; i < L; i++) {
            int nextTurnTime = Integer.parseInt(cmds[i].split(" ")[0]);
            String cmd = cmds[i].split(" ")[1];

            while (time < nextTurnTime) {
                int[] head = dq.peekFirst();
                int nx = head[0] + dx[dir];
                int ny = head[1] + dy[dir];
                time++;

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    System.out.println(time);
                    return;
                }
                if (set.contains(nx + " " + ny)) {
                    System.out.println(time);
                    return;
                }

                if (map[nx][ny] == 1) {
                    map[nx][ny] = 0;
                    dq.addFirst(new int[]{nx, ny});
                    set.add(nx + " " + ny);
                } else {
                    int[] tail = dq.removeLast();
                    set.remove(tail[0] + " " + tail[1]);
                    dq.addFirst(new int[]{nx, ny});
                    set.add(nx + " " + ny);
                }
            }

            if (cmd.equals("L")) {
                dir = (dir + 3) % 4;
            } else {
                dir = (dir + 1) % 4;
            }
        }

        while (true) {
            int[] head = dq.peekFirst();
            int nx = head[0] + dx[dir];
            int ny = head[1] + dy[dir];
            time++;

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) break;
            if (set.contains(nx + " " + ny)) break;

            if (map[nx][ny] == 1) {
                map[nx][ny] = 0;
                dq.addFirst(new int[]{nx, ny});
                set.add(nx + " " + ny);
            } else {
                int[] tail = dq.removeLast();
                set.remove(tail[0] + " " + tail[1]);
                dq.addFirst(new int[]{nx, ny});
                set.add(nx + " " + ny);
            }
        }

        System.out.println(time);
    }
}
