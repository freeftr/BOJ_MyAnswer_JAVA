import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            s = s.toLowerCase();
            int[] arr = new int[26];
            for (int j = 0; j < s.length(); j++) {
                arr[s.charAt(j) - 'a']++;
            }
            dfs(new StringBuilder(), arr, s.length());
        }

        System.out.println(sb);
    }

    static void dfs(StringBuilder cur, int[] arr, int length) {
        if (cur.length() == length) {
            sb.append(cur.toString()).append("\n");
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (arr[i] <= 0) continue;
            arr[i]--;
            String temp = "" + (char) (i + 'a');
            cur.append(temp);
            dfs(cur, arr, length);
            arr[i]++;
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}

/*
조건
- 입력받은 영단어의 철자로 만들수 있는 모든ㄴ 단어 만들기.
- 몇몇 철자 중복 가능. => 한번만 출력
- 알파벳 순서로 출력
 */