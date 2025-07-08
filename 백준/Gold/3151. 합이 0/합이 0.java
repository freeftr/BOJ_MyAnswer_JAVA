import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long answer = 0;
        for (int i = 0; i < N; i++) {
            int cur = arr[i];
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                int sum = cur + arr[left] + arr[right];
                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    if (arr[left] == arr[right]) {
                        answer += (right - left + 1) * (right - left) /2;
                        break;
                    }

                    int l = 1;
                    int r = 1;

                    while (arr[left] == arr[left+1] && left < right - 1) {
                        l++;
                        left++;
                    }
                    while (arr[right] == arr[right-1] && right > left + 1) {
                        r++;
                        right--;
                    }

                    answer += l*r;
                    left++;
                    right--;
                }
            }
        }

        System.out.println(answer);
    }
}
