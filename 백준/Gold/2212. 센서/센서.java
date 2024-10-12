import java.io.*;
import java.util.*;

public class Main {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        if (K >= N) {
            System.out.println(0);
            return;
        }

        int[] map = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(map);
        int ans = map[N - 1] - map[0];
        Integer[] dist = new Integer[N - 1];

        for (int i = 0; i < N - 1; i++) {
            dist[i] = map[i + 1] - map[i];
        }

        Arrays.sort(dist, Collections.reverseOrder());

        for (int i = 0; i < K - 1; i++) {
            ans -= dist[i];
        }

        System.out.println(ans);
    }
}