import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        ArrayList<String> words = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            words.add(br.readLine());
        }

        Collections.sort(words, (a, b) -> {
            if (a.length() == b.length()) {
                int A = 0;
                int B = 0;
                for (int i = 0; i < a.length(); i++) {
                    char aC = a.charAt(i);
                    char bC = b.charAt(i);

                    if (Character.isDigit(aC)) {
                        A += aC - '0';
                    }

                    if (Character.isDigit(bC)) {
                        B += bC - '0';
                    }
                }

                if (A == B) return a.compareTo(b);

                return A - B;
            }
            return a.length() - b.length();
        });

        for (String s : words) {
            System.out.println(s);
        }
    }
}

/*
조건
- 기타마다 시리얼 번호가 다름.
- 정렬 기준
    - 짧은 거 먼저.
    - 길이가 같으면 각 자리수 합이 적은 것. (숫자만 더함)
    - 사전순 비교 (숫자가 알파벳보다 작음)

요구
- 시리얼을 정렬해서 출력
 */