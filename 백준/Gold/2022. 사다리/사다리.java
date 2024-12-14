import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        double x, y, c;
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());
        c = Double.parseDouble(st.nextToken());

        double left = 0;
        double right = Math.min(x, y);

        while (right - left >= 0.001) {
            double mid = (left + right) / 2;
            double h1 = Math.sqrt(x * x - mid * mid);
            double h2 = Math.sqrt(y * y - mid * mid);
            double newC = (h1 * h2) / (h1 + h2);

            if (newC >= c) {
                left = mid;
            } else {
                right = mid;
            }
        }

        System.out.printf("%.3f\n", right);
    }
}