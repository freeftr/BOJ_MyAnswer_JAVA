import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int max = 0;
        int idx = 0;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int S = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            max=0;
            idx=-1;
            for (int j = i+1; j < N; j++) {
                if(arr[j] > arr[i] && j-i<=S){
                    if(arr[j]>=max){
                        max = arr[j];
                        idx = j;
                    }
                }
            }

            if (idx != -1) {
                int temp = arr[idx];
                for (int j = idx; j > i; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[i] = temp;
                S -= idx - i;
            }
        }

        for (int i : arr) {
            System.out.printf("%d ", i);
        }
    }
}