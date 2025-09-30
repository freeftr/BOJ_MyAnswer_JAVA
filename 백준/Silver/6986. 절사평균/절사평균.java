import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Double[] arr = new Double[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Double.parseDouble(br.readLine());
        }

        Arrays.sort(arr, (a, b) -> Double.compare(a, b));

        double julsa = 0;

        double sum = 0;
        for (int i = K; i < N - K; i++) {
            sum += arr[i];
        }

        julsa = sum / (N - K * 2);

        sum = 0;
        double left = arr[K];
        double right = arr[N - 1 - K];
        for (int i = 0; i < N; i++) {
            if (i < K) {
                sum += left;
            } else if (i > N - 1 - K) {
                sum += right;
            } else {
                sum += arr[i];
            }
        }

        double bojung = sum / N;

        System.out.printf("%.2f\n", julsa);
        System.out.printf("%.2f\n", bojung);
    }
}

/*
조건
- 절사평균: 전체 정렬해서 양 끝 제외 평균.
- 보정평균: 양쪽 끝 두개씩 제일 가까운 것으로 평균 내기.
- 소숫점 셋째자리에서 반올림.
 */