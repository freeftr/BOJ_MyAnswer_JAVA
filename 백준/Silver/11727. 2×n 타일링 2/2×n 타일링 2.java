import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println(1);
            return;
        }

        if (n == 2) {
            System.out.println(3);
            return;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 3;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % 10007;
        }

        System.out.println(dp[n]);
    }
}

/*
조건
- 1 x 2, 2 x 1, 2 x 2

요구
- 2 x n 직사격형 채우는 방법의 수 구하기

풀이
- dp
dp[i] = dp[i - 1] + 2 * dp[i - 2]
1 -> 1
2 -> 3
3 -> 5
4 -> 11
5 -> 21

=|
|=
|||
ㅁ|
|ㅁ

ㅁㅁ
ㅁ=
=ㅁ
ㅁ||
|ㅁ|
||ㅁ
=||
|=|
||=
==
||||

ㅁ=|
=ㅁ|
ㅁ|=
=|ㅁ
|=ㅁ
|ㅁ=
==|
=|=
|==
=|||
|=||
||=|
|||=
|||||
ㅁㅁ|
ㅁ|ㅁ
|ㅁㅁ
ㅁ|||
|ㅁ||
||ㅁ|
|||ㅁ
 */