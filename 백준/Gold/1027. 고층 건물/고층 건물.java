import java.io.*;
import java.util.*;
public class Main {

    static int N, ans = 0;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        ans = 0;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int sum = checkL(i) + checkR(i);
            ans = Math.max(ans, sum);
        }
        System.out.println(ans);
    }

    static int checkL(int idx){
        double max = Double.NEGATIVE_INFINITY;
        int cnt = 0;
        for (int i = idx-1; i >=0 ; i--) {
            double line = (double)(arr[i]-arr[idx])/(idx-i);
            if(max<line){
                max = line;
                cnt++;
            }
        }
        return cnt;
    }
    static int checkR(int idx){
        double max = Double.NEGATIVE_INFINITY;
        int cnt = 0;
        for (int i = idx+1; i < N; i++) {
            double line = (double)(arr[i]-arr[idx])/(i-idx);
            if(max<line){
                max = line;
                cnt++;
            }
        }
        return cnt;
    }
}