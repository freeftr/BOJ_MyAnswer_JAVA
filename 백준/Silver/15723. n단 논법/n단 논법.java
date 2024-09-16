import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean check;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < 26; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            char from = s.charAt(0);
            char to = s.charAt(5);
            graph.get(from - 'a').add(to - 'a');
        }

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            char from = s.charAt(0);
            char to = s.charAt(5);

            visited = new boolean[26];
            check = false;

            dfs(from - 'a', to - 'a');

            if (check) {
                System.out.println("T");
            } else {
                System.out.println("F");
            }
        }
    }

    static void dfs(int v, int target) {
        if (v == target) {
            check = true;
            return;
        }

        visited[v] = true;

        for (int nv : graph.get(v)) {
            if (!visited[nv]) {
                dfs(nv, target);
            }
        }
    }
}