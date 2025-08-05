import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[][] rules = new int[K][3];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            rules[i][0] = Integer.parseInt(st.nextToken());
            rules[i][1] = Integer.parseInt(st.nextToken());
            rules[i][2] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (check(mid, K, rules, D)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean check(int mid, int K, int[][] rules, int D) {
        int result = 0;
        for (int i = 0; i < K; i++) {
            if (mid < rules[i][0]) continue;

            int max = Math.min(rules[i][1], mid);

            int length = max - rules[i][0];

            result += length / rules[i][2] + 1;



            if (result >= D) {
                return true;
            }
        }

        return result >= D;
    }
}
