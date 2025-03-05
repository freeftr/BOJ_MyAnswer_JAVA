import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int a = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                a++;
            }
        }

        if (a == s.length() || a == 0) {
            System.out.println(0);
            return;
        }

        int b = 0;
        for (int i = 0; i < a; i++) {
            if (s.charAt(i) == 'b') {
                b++;
            }
        }

        int ans = b;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == 'b') {
                b--;
            }
            if (s.charAt((i + a - 1) % s.length()) == 'b') {
                b++;
            }

            ans = Math.min(ans, b);
        }

        System.out.println(ans);
    }
}
