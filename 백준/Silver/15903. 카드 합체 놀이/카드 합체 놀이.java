import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pq.add(Long.parseLong(st.nextToken()));
        }
        long sum = 0;
        for (int i = 0; i < m; i++) {
            Long one = pq.poll();
            Long two = pq.poll();
            pq.add(one + two);
            pq.add(one + two);
        }

        for (int i = 0; i < n; i++) {
            sum += pq.poll();
        }

        System.out.println(sum);
    }
}
