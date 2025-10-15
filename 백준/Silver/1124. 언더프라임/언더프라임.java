import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        boolean[] isPrime = new boolean[B + 1];

        for (int i = 2; i <= B; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i <= B; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= B; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int answer = 0;
        for (int i = A; i <= B; i++) {
            int cnt = 0;
            int temp = i;

            for (int p = 2; p * p <= temp; p++) {
                while (temp % p == 0) {
                    cnt++;
                    temp /= p;
                }
            }
            if (temp > 1) cnt++;

            if (isPrime[cnt]) answer++;
        }

        System.out.println(answer);
    }
}
