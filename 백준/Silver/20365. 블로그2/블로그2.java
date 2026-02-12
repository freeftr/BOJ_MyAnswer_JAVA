import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String s = br.readLine();
        String[] B = s.split("R");
        String[] R = s.split("B");

        int b = B.length;
        int r = R.length;

        for (int i = 0; i < B.length; i++) {
            if (!B[i].contains("B"))b--;
        }

        for (int i = 0; i < R.length; i++) {
            if (!R[i].contains("R"))r--;
        }

        System.out.println(Math.min(b, r) + 1);
    }
}

/*
조건
- 해결한 문제 파란색
- 못한 문제 빨간색
- 연속된 임의의 문제들을 선택 후 칠하기

요구
- 원하는대로 칠하는 가장 최소한의 횟수

풀이
- 둘 중 더 많은 거를 한번에 더 칠하기
- 안된거를 칠하는 개수 구하기

B R R B R B R R B
 */