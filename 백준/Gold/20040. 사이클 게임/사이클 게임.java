import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (!union(a,b)) {
                System.out.println(i);
                System.exit(0);
            }
        }
        System.out.println(0);
    }

    static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a == b) {
            return false;
        }
        parent[b] = a;
        return true;
    }

    static int find(int v) {
        if (parent[v] == v) {
            return v;
        }
        return parent[v] = find(parent[v]);
    }
}
