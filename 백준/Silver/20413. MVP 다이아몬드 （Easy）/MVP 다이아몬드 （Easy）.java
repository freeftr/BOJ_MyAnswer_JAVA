import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] req = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            req[i] = Integer.parseInt(st.nextToken());
        }

        char[] records = br.readLine().toCharArray();

        int prev = 0;
        int sum = 0;

        for (int i = 0; i < N; i++) {
            char grade = records[i];
            int cur = 0;

            if (grade == 'B') {
                cur = req[0] - 1 - prev;
            } else if (grade == 'S') {
                cur = req[1] - 1 - prev;
            } else if (grade == 'G') {
                cur = req[2] - 1 - prev;
            } else if (grade == 'P') {
                cur = req[3] - 1 - prev;
            } else if (grade == 'D') {
                cur = req[3];
            }

            sum += cur;
            prev = cur;
        }

        System.out.println(sum);
    }
}


/*
조건
- 과금액에 따라 혜택 제공.
- B, S, G, P, D
- 최근 2개월간 과금액으로 결정.
- D 기준액 까지만 과금, 만원단위
- 등급은 달이 끝날때 계산.
- N개월 간 MVP 기록 유출

요구
- 최대 얼마나 과금한건지 구하기

B   S   G
30  60  90  150

1: 30이상 60언더
1 + 2: 60이상 90언더
2 + 3: 90이상 150언더
 */