import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        arr[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) + arr[i - 1];
        }

        if (m == 0) {
            System.out.println(0);
            return;
        }

        int left = 0;
        int right = m - 1;
        long sum = 0;
        sum = arr[right];

        long answer = sum;
        while (left <= right && right < n - 1) {
            left++;
            right++;
            sum = arr[right] - arr[left - 1];
            answer = Math.max(answer, sum);
        }

        System.out.println(answer);
    }
}

/*
조건
- 일마다 급여 있음
- 정해진 일만큼 일함
- n일 후 일급 정보 알음
- m일밖에 일못함.

10  20  30  20  10
10  30  60  80  90
 */
