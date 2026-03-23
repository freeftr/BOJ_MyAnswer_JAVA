import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int a = Integer.parseInt(st.nextToken());
                if (a == 1) {
                    union(i, j);
                }
            }
        }
        HashSet<Integer> set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int a = Integer.parseInt(st.nextToken());
            set.add(find(a));
        }

        if (set.size() == 1) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        parent[a] = b;
    }
}

/*
조건
- N개의 도시
-

요구
- 가능한 경로 인지 판단

풀이
- 분리집합
 */