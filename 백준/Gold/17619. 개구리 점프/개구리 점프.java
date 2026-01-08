import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, Q;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        parent = new int[N];
        for (int i = 0; i < N; i++) parent[i] = i;

        int[][] lumbers = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            lumbers[i][0] = x1;
            lumbers[i][1] = x2;
            lumbers[i][2] = i;
        }

        Arrays.sort(lumbers, (a, b) -> a[0] - b[0]);

        int curRight = lumbers[0][1];
        int rep = lumbers[0][2];

        for (int i = 1; i < N; i++) {
            int x1 = lumbers[i][0];
            int x2 = lumbers[i][1];
            int org = lumbers[i][2];

            if (x1 <= curRight) {
                union(rep, org);
                curRight = Math.max(curRight, x2);
            } else {
                rep = org;
                curRight = x2;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            sb.append(find(a) == find(b) ? 1 : 0).append('\n');
        }

        System.out.print(sb);
    }

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        parent[b] = a;
    }
}


/*
조건
- N개의 통나무
- A -> B 수직방향으로 점프 (다른 통나무를 지나칠 수는 없음)

요구
- A -> B로 갈 수 있는지?

풀이
- 앞의 그룹과 비교해서 겹치며 union
 */
