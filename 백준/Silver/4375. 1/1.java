import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            int n = Integer.parseInt(line);

            int rem = 1 % n;
            int len = 1;

            while (rem != 0) {
                rem = (rem * 10 + 1) % n;
                len++;
            }

            System.out.println(len);
        }
    }
}


/*
조건
- 2와 5로 나누어 떨어지지 않는 정수

요구
- 각 자릿수가 모두 1로만 이루어진 n의 배수를 찾는 프로그램
 */