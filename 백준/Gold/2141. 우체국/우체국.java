import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long[][] towns = new long[N][2];

        long total = 0L;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long idx = Long.parseLong(st.nextToken());
            long pop = Long.parseLong(st.nextToken());
            towns[i][0] = idx;
            towns[i][1] = pop;
            total += pop;
        }

        Arrays.sort(towns, (a, b) -> Long.compare(a[0], b[0]));

        long left = 0L;
        long right = total;
        long answer = 0L;

        for (int i = 0; i < N; i++) {
            left += towns[i][1];
            right -= towns[i][1];
            if (left >= right) {
                answer = towns[i][0];
                break;
            }
        }

        System.out.println(answer);
    }
}
