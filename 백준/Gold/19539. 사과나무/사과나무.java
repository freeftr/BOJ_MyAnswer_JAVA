import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        long sum = 0;
        long twoCount = 0;

        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(st.nextToken());
            sum += h;
            twoCount += h / 2;
        }

        if (sum % 3 == 0 && twoCount >= sum / 3)
            System.out.println("YES");
        else
            System.out.println("NO");
    }
}