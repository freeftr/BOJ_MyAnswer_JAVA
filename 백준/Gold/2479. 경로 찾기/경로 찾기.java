import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ArrayList<String> binaries = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            binaries.add(br.readLine());
        }

        st = new StringTokenizer(br.readLine());

        int s = Integer.parseInt(st.nextToken()) - 1;
        int e = Integer.parseInt(st.nextToken()) - 1;

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        int[] parent = new int[N];

        Arrays.fill(parent, -1);
        visited[s] = true;
        q.add(s);

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == e) break;

            String binary = binaries.get(cur);

            for (int i = 0; i < N; i++) {
                if (visited[i]) continue;
                if (!check(binary, binaries.get(i))) continue;

                parent[i] = cur;
                visited[i] = true;
                q.add(i);
            }
        }

        if (!visited[e]) {
            System.out.println(-1);
            return;
        }

        ArrayList<Integer> path = new ArrayList<>();
        int now = e;
        while (now != -1) {
            path.add(now + 1);
            if (now == s) break;
            now = parent[now];
        }

        if (path.get(path.size() - 1) != s + 1) {
            System.out.println(-1);
            return;
        }

        Collections.reverse(path);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) sb.append(' ');
            sb.append(path.get(i));
        }
        System.out.println(sb.toString());
    }

    static boolean check(String A, String B) {
        int cnt = 0;
        for (int i = 0; i < K; i++) {
            if (A.charAt(i) != B.charAt(i)) cnt++;
        }
        return cnt == 1;
    }
}


/*
조건
- 이진수 코드 A, B
- 해밍 거리 A와 B의 각 비트를 왼쪽부터 오른쪽으로 차례대로 비교할 떄 서로 다른값을 가진 비트의 수.
- N개의 이진 코드, 각각의 길이는 K
- 해밍 경로 인접한 두 코드사이의 해밍 거리가 1인 경로.
-

요구
- 가장 짧은 해밍 경로 구하기.
 */