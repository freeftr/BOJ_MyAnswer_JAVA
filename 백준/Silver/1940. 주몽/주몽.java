import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] components = new int[N];
        for (int i = 0; i < N; i++) {
            components[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(components);

        int left = 0;
        int right = N - 1;

        int answer = 0;

        while (left < right) {
            int sum = components[left] + components[right];

            if (sum < M) {
                left++;
            } else if (sum == M) {
                answer++;
                left++;
            } else {
                right--;
            }
        }

        System.out.println(answer);
    }
}
