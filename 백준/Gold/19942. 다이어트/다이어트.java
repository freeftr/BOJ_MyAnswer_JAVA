import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int mp, mf, ms, mv;
    static int answer = Integer.MAX_VALUE;
    static ArrayList<Integer> result = new ArrayList<>();
    static ArrayList<Food> foods = new ArrayList<>();

    static class Food {
        int idx, p, f, s, v, cost;

        Food(int idx, int p, int f, int s, int v, int cost) {
            this.idx = idx;
            this.p = p;
            this.f = f;
            this.s = s;
            this.v = v;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        mp = Integer.parseInt(st.nextToken());
        mf = Integer.parseInt(st.nextToken());
        ms = Integer.parseInt(st.nextToken());
        mv = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            foods.add(new Food(i, p, f, s, v, cost));
        }

        dfs(0, 0, 0, 0, 0, 0, new ArrayList<>());

        if (answer == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(answer);
        for (int idx : result) {
            System.out.print((idx + 1) + " ");
        }
    }

    static void dfs(int depth, int p, int f, int s, int v, int cost, ArrayList<Integer> chosen) {
        if (cost > answer) return;

        if (check(p, f, s, v)) {
            if (cost < answer || (cost == answer && isLexicographicallySmaller(chosen, result))) {
                answer = cost;
                result = new ArrayList<>(chosen);
            }
            return;
        }

        if (depth == N) return;

        Food cur = foods.get(depth);

        dfs(depth + 1, p, f, s, v, cost, chosen);

        chosen.add(cur.idx);
        dfs(depth + 1, p + cur.p, f + cur.f, s + cur.s, v + cur.v, cost + cur.cost, chosen);
        chosen.remove(chosen.size() - 1);
    }

    static boolean check(int p, int f, int s, int v) {
        return p >= mp && f >= mf && s >= ms && v >= mv;
    }

    static boolean isLexicographicallySmaller(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (b.isEmpty()) return true;
        int len = Math.min(a.size(), b.size());
        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) return a.get(i) < b.get(i);
        }
        return a.size() < b.size();
    }
}

/*
조건
- 식재료 N개 중에서 몇 개 선택해서 성분이 기준치 이상이어야 함.
- 비용은 최소가 될 수 있게

요구
- 최소 식재료 집합 찾기

풀이
- 백트래킹
 */
