import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int F;
    static HashMap<String, Integer> map;
    static int[] parent, size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= t; tc++) {
            parent = new int[200001];
            size = new int[200001];
            map = new HashMap<>();

            for (int i = 0; i < 200001; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            int idx = 0;
            F = Integer.parseInt(br.readLine());

            for (int i = 0; i < F; i++) {
                st = new StringTokenizer(br.readLine());
                String s1 = st.nextToken();
                String s2 = st.nextToken();

                if (!map.containsKey(s1)) {
                    map.put(s1, idx++);
                }
                if (!map.containsKey(s2)) {
                    map.put(s2, idx++);
                }

                int a = map.get(s1);
                int b = map.get(s2);

                sb.append(union(a, b)).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static int find(int v) {
        if (v == parent[v]) return v;
        return parent[v] = find(parent[v]);
    }

    public static int union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            if (size[a] < size[b]) {
                int temp = a;
                a = b;
                b = temp;
            }
            parent[b] = a;
            size[a] += size[b];
        }
        return size[a]; 
    }
}
