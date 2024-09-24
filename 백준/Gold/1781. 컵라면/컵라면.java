import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Problem[] problems = new Problem[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            problems[i] = new Problem(a, b);
        }

        Arrays.sort(problems, (p1, p2) -> p1.deadline - p2.deadline);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Problem p : problems) {
            pq.offer(p.ramen);
            if (pq.size() > p.deadline) {
                pq.poll();
            }
        }

        int ans = 0;
        while (!pq.isEmpty()) {
            ans += pq.poll();
        }

        System.out.println(ans);
    }
}

class Problem {
    int deadline;
    int ramen;

    public Problem(int deadline, int ramen) {
        this.deadline = deadline;
        this.ramen = ramen;
    }
}