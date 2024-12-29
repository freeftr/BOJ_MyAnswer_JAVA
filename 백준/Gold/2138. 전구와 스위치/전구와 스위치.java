import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static char[] original;
    static char[] target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        original = br.readLine().toCharArray();
        target = br.readLine().toCharArray();

        int ans1 = check(original.clone(), true);
        int ans2 = check(original.clone(), false);

        if (ans1 == -1 && ans2 == -1) {
            System.out.println(-1);
        } else if (ans1 == -1) {
            System.out.println(ans2);
        } else if (ans2 == -1) {
            System.out.println(ans1);
        } else {
            System.out.println(Math.min(ans1, ans2));
        }
    }

    private static int check(char[] arr, boolean pressFirst) {
        int count = 0;

        if (pressFirst) {
            pressSwitch(arr, 0);
            count++;
        }

        for (int i = 1; i < N; i++) {
            if (arr[i - 1] != target[i - 1]) {
                pressSwitch(arr, i);
                count++;
            }
        }

        if (arr[N - 1] != target[N - 1]) {
            return -1;
        }

        return count;
    }

    private static void pressSwitch(char[] arr, int idx) {
        arr[idx] = arr[idx] == '0' ? '1' : '0';
        if (idx - 1 >= 0) {
            arr[idx - 1] = arr[idx - 1] == '0' ? '1' : '0';
        }
        if (idx + 1 < N) {
            arr[idx + 1] = arr[idx + 1] == '0' ? '1' : '0';
        }
    }
}