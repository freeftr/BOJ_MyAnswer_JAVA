import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            HashMap<Long, Integer> map = new HashMap<>();
            st = new StringTokenizer(br.readLine());

            int cnt = Integer.parseInt(st.nextToken());

            for (int j = 0; j < cnt; j++) {
                long army = Long.parseLong(st.nextToken());
                map.merge(army, 1, Integer::sum);
            }

            long win = -1;
            for (long key : map.keySet()) {
                if (map.get(key) > cnt / 2) {
                    win = key;
                    break;
                }
            }

            sb.append(win == -1 ? "SYJKGW" : win).append('\n');
        }

        System.out.print(sb);
    }
}
