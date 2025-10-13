import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        HashMap<Long, Integer> map = new HashMap<>();
        long answer = 0;
        int max = 0;

        for (int i = 0; i < N; i++) {
            long key = Long.parseLong(br.readLine());
            map.merge(key, 1, Integer::sum);

            int cnt = map.get(key);
            if (cnt > max || (cnt == max && key < answer)) {
                max = cnt;
                answer = key;
            }
        }

        System.out.println(answer);
    }
}
