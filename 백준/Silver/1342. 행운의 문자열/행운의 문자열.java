import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    static int answer = 0;
    static HashMap<Character, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();

        for (int i = 0; i < S.length(); i++) {
            map.merge(S.charAt(i), 1, Integer::sum);
        }

        dfs(0, S.length(), new StringBuilder());

        System.out.println(answer);
    }

    static void dfs(int depth, int N, StringBuilder sb) {
        if (depth == N) {
            answer++;
            return;
        }

        for (Character c : map.keySet()) {
            int cnt = map.get(c);

            if (cnt == 0) continue;

            if (!sb.isEmpty() && sb.charAt(depth - 1) == c) continue;

            sb.append(c);
            map.put(c, cnt - 1);
            dfs(depth + 1, N, sb);
            sb.deleteCharAt(sb.length() - 1);
            map.put(c, cnt);
        }
    }
}

/*
조건
- 행운의 문자열 = 인접해 있는 모든 문자가 같지 않은 문자열

요구
- 재배치해서 얻은 서로 다른 행운의 문자열 구하기

풀이
- 완탐?
 */