import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String S;
    static boolean ok = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        String T = br.readLine();
        dfs(new StringBuilder(T));
        System.out.println(ok ? 1 : 0);
    }

    static void dfs(StringBuilder t) {
        if (ok) return;
        if (t.length() == S.length()) {
            if (t.toString().equals(S)) ok = true;
            return;
        }
        int last = t.length() - 1;
        if (t.charAt(last) == 'A') {
            t.deleteCharAt(last);
            dfs(t);
            t.append('A');
        }
        if (t.charAt(0) == 'B') {
            t.deleteCharAt(0);
            t.reverse();
            dfs(t);
            t.reverse();
            t.insert(0, 'B');
        }
    }
}
