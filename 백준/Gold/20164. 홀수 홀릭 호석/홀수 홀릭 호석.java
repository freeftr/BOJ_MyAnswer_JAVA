import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int min = Integer.MAX_VALUE;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        dfs(N, 0);
        System.out.println(min + " " + max);
    }

    static void dfs(int num, int sum) {
        int len = String.valueOf(num).length();
        int cnt = countOdd(num);
        sum += cnt;

        if (len == 1) {
            min = Math.min(min, sum);
            max = Math.max(max, sum);
            return;
        }

        if (len == 2) {
            dfs(num % 10 + num / 10, sum);
            return;
        }

        String S = num + "";

        for (int i = 0; i < len - 2; i++) {
            for (int j = i + 1; j < len - 1; j++) {
                for (int k = j + 1; k < len; k++) {
                    int a = Integer.parseInt(S.substring(i, j));
                    int b = Integer.parseInt(S.substring(j, k));
                    int c = Integer.parseInt(S.substring(k, len));

                    dfs(a + b + c, sum);
                }
            }
        }
    }

    static int countOdd(int num) {
        int cnt = 0;

        while (num > 0) {
            int mod = num % 10;
            num /= 10;
            if (mod % 2 != 0) cnt++;
        }

        return cnt;
    }
}

/*
연산
1. 각 자리 숫자 중 홀수의 개수를 적음
2. 한 자리면 종료
3. 두 자리면 2개로 나눠서 합을 구함.
4. 세 자리 이상이면 3개의 수로 분할 후 3개를 더함

요구
- 만들 수 있는 최종값 중 최솟값, 최댓값 구함

풀이
- 재귀 + 완탐
 */
