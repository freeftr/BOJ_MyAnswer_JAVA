import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(1);
            return;
        }

        int left = 1;
        int right = 2;
        int sum = left + right;
        int answer = 0;

        while (right <= N) {
            if (sum < N) {
                right++;
                sum += right;
            } else if (sum == N) {
                answer++;
                sum -= left;
                left++;
            } else {
                sum -= left;
                left++;
            }
        }

        System.out.println(answer);
    }
}
