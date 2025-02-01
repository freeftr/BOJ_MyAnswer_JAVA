import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int ans = 1;
        for (int i = 0; i < N; i++) {
            if (ans < arr[i]) {
                break;
            }
            ans += arr[i];
        }

        System.out.println(ans);
    }
}
