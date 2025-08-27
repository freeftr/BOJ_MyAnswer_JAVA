import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, C;
    static long K;
    static int[] A;
    static long T;
    static boolean ok;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        A = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        long left = 0L;
        long right = (long) 1e12;

        while (left < right) {
            long mid = (left + right) / 2;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    static boolean check(long target) {
        T = target;
        ok = false;
        dfs(0, new ArrayList<Integer>());
        return ok;
    }

    static void dfs(int depth, ArrayList<Integer> list) {
        if (ok) return;
        if (depth == C) {
            int made = calculate(list);
            if (made >= K) ok = true;
            return;
        }

        for (int i = 0; i < N; i++) {
            list.add(i);
            dfs(depth + 1, list);
            list.remove(list.size() - 1);
            if (ok) return;
        }
    }

    static int calculate(ArrayList<Integer> list) {
        int[] cnt = new int[N];
        for (int idx : list) cnt[idx]++;

        long made = 0L;
        for (int i = 0; i < N; i++) {
            int t = A[i] - cnt[i];
            if (t < 1) t = 1;
            made += T / t;
            if (made >= K) return (int) K;
        }
        return (int) made;
    }


}
