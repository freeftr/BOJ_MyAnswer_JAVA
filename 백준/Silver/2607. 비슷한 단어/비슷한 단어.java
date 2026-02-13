import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String first = br.readLine();

        int[] cnt = new int[26];

        for (int i = 0; i < first.length(); i++) {
            cnt[first.charAt(i) - 'A']++;
        }

        int answer = 0;

        for (int i = 0; i < N - 1; i++) {
            String s = br.readLine();

            if (Math.abs(first.length() - s.length()) > 1)
                continue;

            int[] temp = new int[26];

            for (int j = 0; j < s.length(); j++) {
                temp[s.charAt(j) - 'A']++;
            }

            int diff = 0;

            for (int j = 0; j < 26; j++) {
                diff += Math.abs(cnt[j] - temp[j]);
            }

            if (first.length() == s.length()) {
                if (diff == 0 || diff == 2)
                    answer++;
            } else {
                if (diff == 1)
                    answer++;
            }
        }

        System.out.println(answer);
    }
}
