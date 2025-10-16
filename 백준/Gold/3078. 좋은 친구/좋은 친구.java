import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] nm = br.readLine().split(" ");

        int N = Integer.parseInt(nm[0]);
        int K = Integer.parseInt(nm[1]);

        Map<Integer, ArrayDeque<Integer>> map = new HashMap<>();
        long answer = 0;

        for (int i = 0; i < N; i++) {
            int len = br.readLine().length();
            ArrayDeque<Integer> q = map.computeIfAbsent(len, k -> new ArrayDeque<>());

            while (!q.isEmpty() && i - q.peekFirst() > K) {
                q.pollFirst();
            }

            answer += q.size();

            q.addLast(i);
        }

        System.out.println(answer);
    }
}
