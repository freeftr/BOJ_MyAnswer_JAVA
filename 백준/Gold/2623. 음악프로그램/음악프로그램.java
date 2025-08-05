import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] indep;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indep = new int[N+1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int prev = Integer.parseInt(st.nextToken());
            for (int j = 1; j < a; j++) {
                int next = Integer.parseInt(st.nextToken());
                graph.get(prev).add(next);
                indep[next]++;
                prev = next;
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        ArrayList<Integer> answer = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (indep[i] == 0) {
                q.add(i);
            }
        }

        while (!q.isEmpty()) {
            int now = q.poll();
            answer.add(now);

            for (int next : graph.get(now)) {
                indep[next]--;
                if (indep[next] == 0) {
                    q.add(next);
                }
            }
        }

        if (answer.size() != N) {
            System.out.println(0);
        } else {
            for (int order : answer) {
                System.out.println(order);
            }
        }
    }
}
