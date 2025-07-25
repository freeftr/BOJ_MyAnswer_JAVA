import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] parent = new int[N+1];

            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());

                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                parent[B] = A;
            }

            HashSet<Integer> set = new HashSet<>();
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            while(true) {
                set.add(a);
                a = parent[a];
                if (parent[a] == a) {
                    break;
                }
            }

            while(true) {
                if (set.contains(b)) {
                    System.out.println(b);
                    break;
                }
                b = parent[b];
            }
        }
    }
}
