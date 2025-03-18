import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int ans = 0;
        int gcd = gcd(N, M);

        if (N%M==0){
            ans = 0;
        } else {
            ans = M - gcd;
        }

        System.out.println(ans);
    }

    public static int gcd(int a, int b){
        if (b==0){
            return a;
        }
        return gcd(b, a % b);
    }

    public static int lcm(int a, int b){
        return a * b / gcd(a, b);
    }
}
