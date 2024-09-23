import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[] days = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (i == 0) {
                days[i] = a;
            } else {
                days[i] = days[i - 1] + a;
            }
        }

        int lidx = 0;
        int ridx = X;
        int max = days[ridx - 1];
        int cnt = 1;

        while (ridx < N) {
            int sum = days[ridx] - days[lidx];
            if (max == sum) {
                cnt++;
            } else if (max < sum) {
                max = sum;
                cnt = 1;
            }
            lidx++;
            ridx++;
        }

        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(cnt);
        }
    }
}