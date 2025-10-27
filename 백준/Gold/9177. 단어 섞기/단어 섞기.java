import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            String a = s[0];
            String b = s[1];
            String target = s[2];

            boolean result = bfs(a, b, target);

            if (result) {
                sb.append("Data set " + (i + 1) + ": yes\n");
            } else {
                sb.append("Data set " + (i + 1) + ": no\n");
            }
        }

        System.out.println(sb.toString());
    }

    static boolean bfs(String a, String b, String target) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[201][201];

        q.add(new int[]{0,0,0});
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int aIdx = cur[0];
            int bIdx = cur[1];
            int idx = cur[2];

            if (idx == target.length()) return true;

            if (aIdx < a.length() && a.charAt(aIdx) == target.charAt(idx) && !visited[aIdx + 1][bIdx]) {
                q.add(new int[]{aIdx + 1, bIdx, idx + 1});
                visited[aIdx + 1][bIdx] = true;
            }

            if (bIdx < b.length() && b.charAt(bIdx) == target.charAt(idx) && !visited[aIdx][bIdx + 1]) {
                q.add(new int[]{aIdx, bIdx + 1, idx + 1});
                visited[aIdx][bIdx + 1] = true;
            }
        }

        return false;
    }
}

/*
조건
- 두 단어를 섞어서 단어를 만듬.
- 근데 원래 기존 단어 내에서 문자 순서는 바뀌면 안댐.
-
 */
