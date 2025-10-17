import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        StringBuilder T = new StringBuilder(br.readLine());

        while (!T.toString().equals(S) && T.length() >= S.length()) {
            if (T.charAt(T.length() - 1) == 'A') {
                T.deleteCharAt(T.length() - 1);
            } else {
                T.deleteCharAt(T.length() - 1);
                T.reverse();
            }
        }

        System.out.println(T.toString().equals(S) ? 1 : 0);
    }
}

/*
조건
- A와 B로 이루어진 영어 단어.
- S를 T로 바꿈.
- 문자열 뒤에 A 추가
- 문자열 뒤집고 B 추가

요구
- 만들 수 있는지 없는지.
 */