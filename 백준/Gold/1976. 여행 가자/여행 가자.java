import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int a = Integer.parseInt(st.nextToken());
                if (i == j) continue;

                if (a == 1) {
                    union(i, j);
                }
            }
        }

        ArrayList<Integer> plan = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            plan.add(Integer.parseInt(st.nextToken()));
        }

        HashSet<Integer> ps = new HashSet<>();
        for (int a : plan) {
            ps.add(find(a));
        }

        if (ps.size() != 1) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
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
- N개 도시
- 가능한 여행 경로인지 판별
- 같은 도시 여러번 방문 가능

요구
- 가능 경로 판별

풀이
- 같은 집합인지 구분
 */