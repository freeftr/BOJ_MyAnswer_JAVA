import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String line = br.readLine();
            if (line == null) break;

            StringTokenizer st = new StringTokenizer(line);
            long n = Long.parseLong(st.nextToken());
            long k = Long.parseLong(st.nextToken());

            if (n == 0 && k == 0) break;

            k = Math.min(k, n - k);

            long result = 1;
            for (long i = 1; i <= k; i++) {
                result = result * (n - i + 1) / i;
            }

            sb.append(result).append("\n");
        }

        System.out.print(sb);
    }
}
