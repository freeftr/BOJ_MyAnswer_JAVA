import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int max0, max1, max2;
        int min0, min1, min2;

        st = new StringTokenizer(br.readLine());
        max0 = min0 = Integer.parseInt(st.nextToken());
        max1 = min1 = Integer.parseInt(st.nextToken());
        max2 = min2 = Integer.parseInt(st.nextToken());

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int nMax0 = Math.max(max0, max1) + a;
            int nMax1 = Math.max(Math.max(max0, max1), max2) + b;
            int nMax2 = Math.max(max1, max2) + c;

            int nMin0 = Math.min(min0, min1) + a;
            int nMin1 = Math.min(Math.min(min0, min1), min2) + b;
            int nMin2 = Math.min(min1, min2) + c;

            max0 = nMax0;
            max1 = nMax1;
            max2 = nMax2;

            min0 = nMin0;
            min1 = nMin1;
            min2 = nMin2;
        }

        int answerMax = Math.max(Math.max(max0, max1), max2);
        int answerMin = Math.min(Math.min(min0, min1), min2);

        System.out.println(answerMax + " " + answerMin);
    }
}