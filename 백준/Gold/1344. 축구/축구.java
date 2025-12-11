import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());

        double[] dpA = new double[90/5 + 1];
        double[] dpB = new double[90/5 + 1];
        boolean[] isPrime = new boolean[19];
        Arrays.fill(isPrime, true);

        double a = (double) A / 100;
        double b = (double) B / 100;

        dpA[0] = 1.0;
        dpB[0] = 1.0;

        for (int i = 0; i < 18; i++) {
            for (int j = i + 1; j > 0; j--) {
                dpA[j] = dpA[j] * (1 - a) + dpA[j - 1] * a;
                dpB[j] = dpB[j] * (1 - b) + dpB[j - 1] * b;
            }

            dpA[0] *= (1 - a);
            dpB[0] *= (1 - b);
        }

        for (int i = 2; i <= 18; i++) {
            for (int j = i + i; j <= 18; j += i) {
                if (isPrime[j]) isPrime[j] = false;
            }
        }

        isPrime[0] = false;
        isPrime[1] = false;

        double notPrimeA = 0;
        double notPrimeB = 0;

        for (int i = 0; i <= 18; i++) {
            if (!isPrime[i]) {
                notPrimeA += dpA[i];
                notPrimeB += dpB[i];
            }
        }

        double result = 1 - (notPrimeA * notPrimeB);

        System.out.println(result);
    }
}

/*
조건
- 90분을 5분 간격으로 나눔.
- A, B 팀이 득점할 확률이 주어짐.

요구
- 적어도 한 팀이 소수로 득점할 확률 구하기

풀이
- 1 - 둘다 소수가 아닌 수로 득점할 확률 구하기.
 */