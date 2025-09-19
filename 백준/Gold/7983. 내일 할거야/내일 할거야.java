import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            arr[i][0] = d;
            arr[i][1] = t;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[1] == b[1]) {
                return b[0] - a[0];
            }
            return a[1] - b[1];
        });

        int day = 0;
        for (int i = n - 1; i >= 0; i--) {
            int[] cur = arr[i];
            if (i == n - 1) {
                day = cur[1] - cur[0];
                continue;
            }
            if (cur[1] < day) {
                day = cur[1] - cur[0];
            } else {
                day -= cur[0];
            }
        }

        System.out.println(day);
    }
}

/*
2 8  => 6
1 13 => 12
3 10 => 7

1 13  12
3 10
2 8
 */
