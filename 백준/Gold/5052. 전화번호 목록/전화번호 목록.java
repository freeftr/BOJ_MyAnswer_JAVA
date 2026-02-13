import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            ArrayList<String> numbers = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                numbers.add(s);
            }

            Collections.sort(numbers);
            boolean found = false;

            for (int i = 0; i < n - 1; i++) {
                if (numbers.get(i + 1).startsWith(numbers.get(i))) {
                    found = true;
                    break;
                }
            }

            sb.append(found ? "NO" : "YES").append("\n");
        }

        System.out.println(sb);
    }
}

/*
조건
- 전화번호 목록
- 한 번호가 다른 번호의 접두어가 되면 안된다.

요구
- 일관성이 있는지 없는지

풀이
-
 */