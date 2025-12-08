import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int L, C;
    static char[] arr;
    static char[] picked;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new char[C];
        picked = new char[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);
        dfs(0, 0);
    }

    static void dfs(int depth, int start) {
        if (depth == L) {
            if (isValid(picked)) {
                System.out.println(new String(picked));
            }
            return;
        }

        for (int i = start; i < C; i++) {
            picked[depth] = arr[i];
            dfs(depth + 1, i + 1);
        }
    }

    static boolean isValid(char[] pwd) {
        int vowel = 0, consonant = 0;
        for (char c : pwd) {
            if (isVowel(c)) vowel++;
            else consonant++;
        }
        return vowel >= 1 && consonant >= 2;
    }

    static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
