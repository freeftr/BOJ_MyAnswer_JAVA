import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, k;
    static int[] A;
    static int[] parent;
    static HashMap<Integer, Integer> cost = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost.put(i, Integer.parseInt(st.nextToken()));
        }

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (find(u) != find(v)) {
                union(u, v);
            }
        }

        HashSet<Integer> set = new HashSet<>();
        long sum = 0;
        for (int i = 1; i <= N; i++) {
            set.add(find(parent[i]));
        }

        for (int key : set) {
            sum += cost.get(key);
        }

        System.out.println(sum <= k ? sum : "Oh no");

    }

    static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        int cA = cost.get(a);
        int cB = cost.get(b);

        if (cA > cB) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
}

/*
조건
- 친구에게 돈주면 한달동안 친구해줌.
- 친구의 친구 = 친구

요구
- 가장 적은 비용으로 모든 사람가 친구

풀이
- 크루스칼?
 */