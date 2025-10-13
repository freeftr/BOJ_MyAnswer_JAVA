import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static HashMap<Integer, ArrayList<int[]>> points = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            points.computeIfAbsent(y, k -> new ArrayList<>()).add(new int[]{x, y});
        }

        System.out.println(bfs(T));
    }

    static int bfs(int T) {
        Queue<int[]> q = new ArrayDeque<>();
        HashSet<String> visited = new HashSet<>();
        visited.add(0 + " " + 0);
        q.add(new int[]{0, 0, 0});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int cnt = cur[2];

            if (y == T) return cnt;

            for (int i = y - 2; i <= y + 2; i++) {
                if (i < 0) continue;
                if (!points.containsKey(i)) continue;

                ArrayList<int[]> next = points.get(i);
                for (int[] pos : next) {
                    int nx = pos[0];
                    int ny = pos[1];

                    if (Math.abs(nx - x) > 2) continue;
                    if (visited.contains(nx + " " + ny)) continue;

                    visited.add(nx + " " + ny);
                    q.add(new int[]{nx, ny, cnt + 1});
                }
            }
        }

        return - 1;
    }
}

/*
조건
- n개의 홈.
- T까지 등반.
- |a - x| <= 2 |b - y| <= 2 이어야만 (a, b)로 이동 가능.
 */