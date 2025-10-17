import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static ArrayList<Long> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= 10; i++) {
            dfs(new StringBuilder(), i, 9);
        }
        Collections.sort(result);

        if (result.size() < N) {
            System.out.println(-1);
            return;
        }
        System.out.println(result.get(N - 1));
    }

    static void dfs(StringBuilder cur, int target, int start) {
        if (cur.length() == target) {
            result.add(Long.parseLong(cur.toString()));
            return;
        }

        for (int i = start; i >= 0; i--) {
            cur.append(i);
            dfs(cur, target, i - 1);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
