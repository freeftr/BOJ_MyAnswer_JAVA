import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] >= S) { 
                System.out.println(1);
                return;
            }
        }

        int left = 0, right = 0;
        int sum = arr[0];
        int minLength = Integer.MAX_VALUE;

        while (right < N) {
            if (sum >= S) {  
                minLength = Math.min(minLength, right - left + 1);
                sum -= arr[left++];
            } else {  
                right++;
                if (right < N) sum += arr[right];
            }
        }

        System.out.println(minLength == Integer.MAX_VALUE ? 0 : minLength);
    }
}
