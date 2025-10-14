import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int S = Integer.parseInt(br.readLine());

        Queue<int[]> q = new ArrayDeque<>();

        q.add(new int[]{1, 0, 0});
        boolean[][] visited = new boolean[2001][2001];
        visited[1][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int s = cur[0];
            int c = cur[1];
            int t = cur[2];

            if (s == S) {
                System.out.println(t);
                return;
            }

            if (!visited[s][s]) {
                visited[s][s] = true;
                q.add(new int[]{s, s, t + 1});
            }

            if (c > 0 && c + s <= 2000 && !visited[s + c][c]) {
                visited[s + c][c] = true;
                q.add(new int[]{c + s, c, t + 1});
            }

            if (s > 0 && !visited[s - 1][c]) {
                visited[s - 1][c] = true;
                q.add(new int[]{s - 1, c, t + 1});
            }
        }
    }
}

/*
조건
- 이모키놐 S개 만들기
- 전체 복사
- 붙여넣기
- 그중에 하나 삭제.

요구
- S개 만드는 데 걸리는 최소 시간 구하기.

풀이
- bfs
 */
