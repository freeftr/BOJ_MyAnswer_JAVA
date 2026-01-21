import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        HashMap<String, String> info = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String[] s = br.readLine().split(" is ");
            info.put(s[0], s[1]);
        }

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            String[] s = br.readLine().split(" is ");
            String a = s[0];
            String b = s[1];

            boolean ok = false;

            while (info.containsKey(a)) {
                a = info.get(a);

                if (a.equals(b)) {
                    ok = true;
                    break;
                }
            }

            sb.append(ok ? "T" : "F").append('\n');
        }

        System.out.print(sb);
    }
}
