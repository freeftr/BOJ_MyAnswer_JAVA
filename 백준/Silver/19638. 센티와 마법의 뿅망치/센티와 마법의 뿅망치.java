import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());
            pq.add(height);
        }

        int cnt = 0;

        for (int i = 0; i < T; i++) {
            int top = pq.poll();

            if (top < H) {
                pq.add(top);
                break;
            }

            if (top == 1) {
                pq.add(top);
                break;
            }

            top /= 2;
            pq.add(top);
            cnt++;
        }

        if (pq.peek() < H) {
            System.out.println("YES");
            System.out.println(cnt);
        } else {
            System.out.println("NO");
            System.out.println(pq.peek());
        }
    }
}