import java.io.*;
import java.util.*;

public class Main {

    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int d = 0; d <= 9; d++) {
            dfs(d, d);
        }

        Collections.sort(list);
        if (N >= list.size()) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(N));
        }
    }

    static void dfs(int last, long num) {
        list.add(num);
        for (int next = 0; next < last; next++) {
            dfs(next, num * 10 + next);
        }
    }
}
