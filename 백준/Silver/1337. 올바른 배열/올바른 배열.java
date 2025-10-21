import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int answer = 5;

        for (int i = 0; i < N; i++) {
            int count = 0;
            int end = arr[i] + 4;

            for (int j = i; j < N; j++) {
                if (arr[j] <= end) count++;
                else break;
            }

            answer = Math.min(answer, 5 - count);
        }

        System.out.println(answer);
    }
}
