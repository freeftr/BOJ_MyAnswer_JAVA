import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String s = br.readLine();

        int left = 0;
        int right = 0;
        int[] cnts = new int[26];
        cnts[s.charAt(0) - 'a']++;
        int cnt = 1;
        int answer = 1;

        while (right < s.length()) {
            int len = right - left + 1;

            if (cnt <= N) {
                right++;
                answer = Math.max(len, answer);
                if (right == s.length()) break;

                cnts[s.charAt(right) - 'a']++;

                if (cnts[s.charAt(right) - 'a'] == 1) cnt++;
            } else {
                if (cnts[s.charAt(left) - 'a'] == 1) {
                    cnt--;
                }
                cnts[s.charAt(left) - 'a']--;
                left++;
            }
        }

        System.out.println(answer);
    }
}

/*
조건
- 고양이 번역
- N개 종류의 알파벳을 가진 연속된 문자열 인식

요구
- 번역할 수 있는 최대 문자열 길이 구하기

풀이
- 투포인터로 돌면서 최대 길이 구하기
 */