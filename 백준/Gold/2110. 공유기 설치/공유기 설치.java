import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int answer = 0;

        int left = 0;
        int right = arr[N-1];

        while(left <= right) {
            int mid = (left + right) / 2;
            if (check(C, arr, mid)) {
                answer = left;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(left);
    }

    static boolean check(int C, int[] arr, int mid) {
        int cnt = 1;
        int last = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - last > mid) {
                cnt++;
                last = arr[i];
            }
        }

        if (cnt >= C) {
            return true;
        } else {
            return false;
        }
    }
}
