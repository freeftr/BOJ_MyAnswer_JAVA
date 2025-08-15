import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long min = Math.min(A, B);
        long max = Math.max(A, B);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < gcd(min, max); i++) {
            sb.append("1");
        }

        System.out.println(sb.toString());
    }

    static long gcd(long a, long b) {
        if (b==0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
