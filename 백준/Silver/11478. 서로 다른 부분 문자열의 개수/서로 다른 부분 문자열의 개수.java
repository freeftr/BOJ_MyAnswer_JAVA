import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        HashSet<String> set = new HashSet<>();

        for (int len = 0; len < s.length(); len++) {
            for (int i = 0; i < s.length() - len; i++) {
//                System.out.println(s.substring(i, i + len));
                set.add(s.substring(i, i + len + 1));
            }
        }

        System.out.println(set.size());
    }
}

/*
조건
- 문자열 S의 서로 다른 부분 문자열 개수 구하기.
- 부분문자열은 S에서 연속된 일부분. 길이가 1보다 크거나 같아야 한다.

요구
- 서로 다른 부분문자열 개수 구하기

풀이
- 그냥 다 잘라서 셋에 넣으면 안대나
 */