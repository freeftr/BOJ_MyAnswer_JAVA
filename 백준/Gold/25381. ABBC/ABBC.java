import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        int answer = 0;
        int c = 0, b = 0;

        for (int i = s.length() - 1; i >= 0 ; i--) {
            char cur = s.charAt(i);

            if (cur == 'C') c++;
            if (cur == 'B') {
                if (c > 0) {
                    answer++;
                    c--;
                } else {
                    b++;
                }
            }
            if (cur == 'A') {
                if (b > 0) {
                    answer++;
                    b--;
                }
            }
        }
        System.out.println(answer);
    }
}

/*
요구
- 1: A와 그 뒤에 있는 B 지우기
- 2: B와 그 뒤에 있는 C 지우기

풀이
- 지울 수 있는 CB 지우기
 */