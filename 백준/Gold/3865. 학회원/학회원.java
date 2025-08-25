import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static HashMap<String, Set<String>> map = new HashMap<>();
    static HashSet<String> result = new HashSet<>();
    static HashSet<String> visited = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = 1;

        while (N != 0) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            map.clear();
            result.clear();
            visited.clear();

            String first = "";
            for (int i = 0; i < N; i++) {
                String[] s = br.readLine().split(":", 2);
                String key = s[0].trim();
                if (i == 0) first = key;

                String rhs = (s.length > 1 ? s[1] : "").replace(".", "").trim();
                String[] valueTemp = rhs.isEmpty() ? new String[0] : rhs.split(",");

                Set<String> values = new HashSet<>();
                for (String v : valueTemp) {
                    v = v.trim();
                    if (!v.isEmpty()) values.add(v);
                }
                map.put(key, values);
            }

            expand(first);
            sb.append(result.size()).append("\n");
        }

        System.out.print(sb.toString());
    }

    static void expand(String key) {
        if (visited.contains(key)) return;
        visited.add(key);

        Set<String> values = map.get(key);
        if (values == null) return;

        for (String v : values) {
            if (map.containsKey(v)) {
                expand(v);
            } else {
                result.add(v);
            }
        }
    }
}
