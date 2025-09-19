import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        @SuppressWarnings("unchecked")
        Queue<String>[] queues = new ArrayDeque[N];

        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            queues[i] = new ArrayDeque<>();
            for (String a : s) {
                queues[i].add(a);
            }
        }

        String[] words = br.readLine().split(" ");

        for (String word : words) {
            boolean flag = false;

            for (Queue<String> q : queues) {
                if (!q.isEmpty() && q.peek().equals(word)) {
                    q.poll();
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                System.out.println("Impossible");
                return;
            }
        }

        for (Queue<String> q : queues) {
            if (!q.isEmpty()) {
                System.out.println("Impossible");
                return;
            }
        }

        System.out.println("Possible");
    }
}
