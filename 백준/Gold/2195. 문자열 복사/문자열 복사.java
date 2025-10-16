import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String P = br.readLine();

        int answer = 0;
        int idx = 0;
        for (int i = 0; i < P.length(); i++) {
            if (S.contains(P.substring(idx, i + 1))) continue;
            idx = i;
            answer++;
        }

        System.out.println(answer + 1);
    }
}

/*
조건
- copy(s, p): S의 s번 문자부터 p개의 문자를 P에 복사해서 붙이기.

요구
- copy를 적게에서 S에서 P를 만들기
- 최소횟수 구하기.

풀이
- 가장 길게 해서 완탐
xy0z
zzz0yyy0xxx
 */