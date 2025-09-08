import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int answer = 1;
        int[] arr = new int[N];
        int[] cnt = new int[50];
        int top = 0;
        int two = 0;
        int one = 1;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            cnt[arr[i]]++;
            top = Math.max(top, arr[i]);
        }

        int idx = 0;
        int prev = cnt[idx];
        while (idx < top) {
            if (cnt[idx] > 2) {
                System.out.println(0);
                return;
            }

            if (idx > N) {
                System.out.println(0);
                return;
            }

            if (cnt[idx + 1] == 0 && idx != top - 1) {
                System.out.println(0);
                return;
            }

            if (cnt[idx + 1] > prev) {
                System.out.println(0);
                return;
            }

            if (cnt[idx] == 2) {
                two++;
            }

            if (cnt[idx] == 1) {
                one = 2;
            }
            idx++;
            prev = cnt[idx];
        }

        if (cnt[0] == 0 || cnt [0] > 2) {
            System.out.println(0);
            return;
        } else if (cnt[top] == 2) {
            two++;
        } else if (cnt[top] == 1){
            one = 2;
        }

        answer = (int) Math.pow(2, two) * one;
        System.out.println(answer);
    }
}