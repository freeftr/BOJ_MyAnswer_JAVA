import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;

public class Main {

    static HashSet<String> set = new HashSet<>();
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            String s = br.readLine().replace(" ", "");
            sb.append(s);
        }

        System.out.println(bfs(sb.toString()));
    }

    static int bfs(String s) {
        final String GOAL = "123456780";

        if (s.equals(GOAL)) return 0;

        set.clear();
        Queue<String> q = new ArrayDeque<>();
        q.add(s);
        set.add(s);

        int depth = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                String cur = q.poll();
                if (cur.equals(GOAL)) return depth;

                int idx = cur.indexOf('0');
                int x = idx / 3;
                int y = idx % 3;

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (nx < 0 || ny < 0 || nx >= 3 || ny >= 3) continue;

                    int nIdx = nx * 3 + ny;

                    char[] arr = cur.toCharArray();
                    char tmp = arr[idx];
                    arr[idx] = arr[nIdx];
                    arr[nIdx] = tmp;

                    String next = new String(arr);
                    if (set.contains(next)) continue;

                    set.add(next);
                    q.add(next);
                }
            }
            depth++;
        }

        return -1;
    }
}
