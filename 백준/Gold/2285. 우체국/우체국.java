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
        int[][] towns = new int[N][2];

        long totalPop = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            towns[i][0] = Integer.parseInt(st.nextToken());
            towns[i][1] = Integer.parseInt(st.nextToken());
            totalPop += towns[i][1];
        }

        Arrays.sort(towns, (a, b) -> a[0] - b[0]);

        long half = (totalPop + 1) / 2;
        long prefix = 0;
        for (int i = 0; i < N; i++) {
            prefix += towns[i][1];
            if (prefix >= half) {
                System.out.println(towns[i][0]);
                break;
            }
        }
    }
}
