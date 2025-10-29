import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        int N = Integer.parseInt(br.readLine());
        String[] A = new String[N];

        for (int i = 0; i < N; i++) {
            A[i] = br.readLine();
        }

        boolean[] dp = new boolean[S.length() + 1];
        dp[S.length()] = true;

        for (int i = S.length() - 1; i >= 0; i--) {
            for (String w : A) {
                int len = w.length();
                if (i + len <= S.length() && S.substring(i, i + len).equals(w) && dp[i + len]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        System.out.println(dp[0] ? 1 : 0);
    }
}


/*
조건
- 소문자 문자열 S
- 단어 목록 A
- S를 A에 포함된 문자열을 한 개 이상 공백없이 붙여서 만들수 있는 없는지.

요구
- A에 있는 단어들로 S를 만들수 있는지 없는지
- 중복 사용 가능.
 */
