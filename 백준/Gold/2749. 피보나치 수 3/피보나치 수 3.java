import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int p = 1500000;
        long n = Long.parseLong(br.readLine()) % p;
        long[] fibo = new long[p+1];
        fibo[0] = 1;
        fibo[1] = 1;


        for (int i = 2; i <= p; i++) {
            fibo[i] = (fibo[i-1] + fibo[i-2])%1000000;
        }

        System.out.println(fibo[(int)n-1]);
    }
}