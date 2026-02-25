import java.io.*;
import java.util.*;

public class Main {

    static int N, K, P;
    static String X;

    static String[] display = {
            "1110111",
            "0010010",
            "1011101",
            "1011011",
            "0111010",
            "1101011",
            "1101111",
            "1010010",
            "1111111",
            "1111011"
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = st.nextToken();

        int[] x = new int[K];
        for (int i = 0; i < K; i++) {
            int idx = X.length() - K + i;
            if (idx >= 0) x[i] = X.charAt(idx) - '0';
            else x[i] = 0;
        }

        int current = Integer.parseInt(X);
        int answer = 0;

        for (int num = 1; num <= N; num++) {

            int temp = num;
            int sum = 0;

            for (int i = K - 1; i >= 0; i--) {
                int d = temp % 10;
                temp /= 10;

                sum += getDiff(x[i], d);
                if (sum > P) break;
            }

            if (sum <= P && num != current) answer++;
        }

        System.out.println(answer);
    }

    static int getDiff(int a, int b) {
        String A = display[a];
        String B = display[b];

        int cnt = 0;
        for (int i = 0; i < 7; i++) {
            if (A.charAt(i) != B.charAt(i)) cnt++;
        }
        return cnt;
    }
}

/*
조건
- 반전: 켜진 부분끄고 꺼진 부분 키기
- k 자리 수
- P개를 반전시킨다고 한다.
- 1 ~ N 사이의 수로 반전시킨다.

요구
- LED를 올바르게 반전시키는 경우의 수 구하기

풀이
- 각 가중치를 구해야할듯 일단

 _
| |
 -
| |
 -

0 -> 1 = 4
0 -> 2
 */