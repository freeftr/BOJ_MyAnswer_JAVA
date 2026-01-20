import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    static ArrayList<String> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        String s = Long.toBinaryString(K + 1);
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < s.length(); i++) {
            sb.append(s.charAt(i) == '0' ? '4' : '7');
        }

        System.out.println(sb.toString());
    }

}

/*
조건
- 4와 7로 이루어진 수

요구
- K번째 작은 수 구하기

풀이
- 규칙 찾아야 할듯

4
7       2

44
47
74
77      4

444
447
474
477
744
747
774
777     8
 */