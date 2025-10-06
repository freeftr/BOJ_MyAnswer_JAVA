import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < t; tc++) {
            int M = nextInt();

            PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> b - a);
            PriorityQueue<Integer> min = new PriorityQueue<>();

            int[] result = new int[(M + 1) / 2];
            int idx = 0;

            for (int i = 0; i < M; i++) {
                int a = nextInt();

                if (max.isEmpty() || a <= max.peek()) max.add(a);
                else min.add(a);

                if (max.size() > min.size() + 1) {
                    min.add(max.poll());
                }
                if (max.size() < min.size()) {
                    max.add(min.poll());
                }

                if (i % 2 == 0) {
                    result[idx++] = max.peek();
                }
            }

            sb.append(result.length).append("\n");
            for (int i = 0; i < result.length; i++) {
                sb.append(result[i]).append(" ");
                if ((i + 1) % 10 == 0) sb.append("\n");
            }
            if (result.length % 10 != 0) sb.append("\n");
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        while (st == null || !st.hasMoreTokens()) {
            String line = br.readLine();
            while (line != null && line.trim().isEmpty()) line = br.readLine();
            if (line == null) throw new IOException("Unexpected EOF");
            st = new StringTokenizer(line);
        }
        return Integer.parseInt(st.nextToken());
    }
}
